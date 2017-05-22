package task7;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.FundPriceHistoryBean;
import task7.databeans.PositionBean;
import task7.databeans.TransactionBean;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;
import task7.model.TransactionDAO;

public class TransitionDayAction extends Action {
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	
	Date executeDate;
	
	
	public TransitionDayAction(Model model) {
//		fundDAO = model.getFundDAO();
//		fundPriceHistoryDAO =  model.getFundPriceHistoryDAO();
//		transactionDAO = model.getTransactionDAO();
//		customerDAO = model.getCustomerDAO();
//		positionDAO = model.getPositionDAO();
//	}
	}
	
	@Override
	public String getName() {
		return "transactionDay.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		//Validate login.
		if (request.getSession().getAttribute("employee") == null) {
			return "login.do";
		}
		
		try {
			FundBean[] tmp = fundDAO.getFund();
			request.setAttribute("fundList", tmp);
			FundPriceHistoryBean pre = fundPriceHistoryDAO.getNewestPrice(1);
			Date lastDate = null;
			if (pre != null) lastDate = pre.getPriceDate();
			request.setAttribute("lastDate", lastDate);
			
			if (request.getParameter("start") == null) {
				return "transactionDay.jsp";
			}
			
			//Validate the inputs.
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date = request.getParameter("executeDate");
			if (date == null || date.length() == 0) {
				errors.add("Please enter a execute date!");
				return "transactionDay.jsp";
			}
			executeDate = new Date(formatter.parse(date).getTime());
			int count = 1;
			double[] prices = new double[tmp.length];
			boolean neg = false;
			while (request.getParameter("fund" + count) != null && request.getParameter("fund" + count).length() != 0) {
				prices[count - 1] = Double.parseDouble(request.getParameter("fund" + count));
				if (prices[count - 1] < 0) {
					neg = true;
					break;
				}
				count++;
			}
			if ((count - 1) != tmp.length) {
				if (neg) errors.add("Please enter a non-negative price for funds!");
				else errors.add("Please enter a price for all of the funds!");
				return "transactionDay.jsp";
			}
			
			//Update the price history table.
			for (int i = 1; i <= prices.length; i++) {
				double price = prices[i - 1];
				FundPriceHistoryBean newest = fundPriceHistoryDAO.getNewestPrice(i);
				if (newest == null || newest.getPriceDate().before(executeDate)) {
					FundPriceHistoryBean priceHistory = new FundPriceHistoryBean();
					priceHistory.setFundId(i);
					priceHistory.setPrice(price);
					priceHistory.setPriceDate(executeDate);
					fundPriceHistoryDAO.create(priceHistory);
				} else {
					errors.add("Execute date must be greater than " + lastDate);
					return "transactionDay.jsp";
				}
			}
			
			//Process all the pending transactions.
			TransactionBean[] pending = transactionDAO.getPending();
			for (TransactionBean p : pending) {
				switch (p.getTransactionType()) {
				case "SELL": 
					processSell(p);
					break;
				case "BUY": 
					processBuy(p);
					break;
				case "DEPOSIT":
					processDeposit(p);
					break;
				case "REQUEST": 
					processRequest(p);
					break;
				}
			}
			return "transactionDay.do";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (ParseException e) {
			errors.add("Please enter data as yyyy-mm-dd!");
			return "transactionDay.jsp";
		} catch (NumberFormatException e) {
			errors.add("Please enter a valid number for price!");
			return "transactionDay.jsp";
		}
	}
	
	private void processBuy(TransactionBean p) throws RollbackException {
		double price = fundPriceHistoryDAO.getNewestPrice(p.getFundId()).getPrice();
		double shares = p.getAmount() / price;
		
		p.setExecuteDate(executeDate);
		p.setShares(shares);
		p.setPrice(price);
		transactionDAO.update(p);
		
		CustomerBean customer = customerDAO.read(p.getCustomerId());
		customer.setCash(customer.getCash() - p.getAmount());
		customerDAO.update(customer);
		
		PositionBean position = positionDAO.read(p.getCustomerId(), p.getFundId());
		if (position == null) {
			position = new PositionBean();
			position.setCustomerId(p.getCustomerId());
			position.setFundName(p.getFundName());
			position.setFundId(p.getFundId());
			position.setShares(shares);
			positionDAO.create(position);
		} else {
			position.setShares(shares + position.getShares());
			positionDAO.update(position);
		}
	}

	private void processSell(TransactionBean p) throws RollbackException {
		double price = fundPriceHistoryDAO.getNewestPrice(p.getFundId()).getPrice();
		double amount = p.getShares() * price;
		
		p.setExecuteDate(executeDate);
		p.setAmount(amount);
		p.setPrice(price);
		transactionDAO.update(p);
		
		PositionBean position = positionDAO.read(p.getCustomerId(), p.getFundId());
		double shareInPosition = position.getShares();
		double shareInTransaction = p.getShares();
		if (shareInPosition - shareInTransaction == 0) {
			positionDAO.delete(p.getCustomerId(), p.getFundId());
		} 
		
		CustomerBean customer = customerDAO.read(p.getCustomerId());
		customer.setCash(customer.getCash() + p.getAmount());
		customer.setAvailable(customer.getAvailable() + amount);
		customerDAO.update(customer);
		
	}
	
	private void processRequest(TransactionBean p) throws RollbackException {
		p.setExecuteDate(executeDate);
		transactionDAO.update(p);
		
		CustomerBean customer = customerDAO.read(p.getCustomerId());
		customer.setCash(customer.getCash() - p.getAmount());
		customerDAO.update(customer);
	}
	
	private void processDeposit(TransactionBean p) throws RollbackException {
		p.setExecuteDate(executeDate);
		transactionDAO.update(p);
		CustomerBean customer = customerDAO.read(p.getCustomerId());
		customer.setCash(customer.getCash() + p.getAmount());
		customerDAO.update(customer);
	}
	
}
