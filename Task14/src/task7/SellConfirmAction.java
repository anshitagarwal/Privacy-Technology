package task7;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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

public class SellConfirmAction extends Action {
	
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public SellConfirmAction(Model model) {
//		fundDAO = model.getFundDAO();
//		transactionDAO = model.getTransactionDAO();
//		customerDAO = model.getCustomerDAO();
//		positionDAO = model.getPositionDAO();
//		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	@Override
	public String getName() {
		return "sellConfirm.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		if (request.getSession().getAttribute("customer") == null) {
			errors.add("Please login as a customer first");
			return "login.do";
		}
		
		String confirmId = request.getParameter("confirmid");
		int fundId;
		try {
			fundId = Integer.parseInt(confirmId);
		} catch (NumberFormatException e) {
			errors.add("Invalid fund id!");
			return "error.jsp";
		}
		
		try {
			FundBean fund = fundDAO.read(fundId);
			request.setAttribute("fund", fund);
			
			CustomerBean customer = (CustomerBean) request.getSession().getAttribute("customer");
			int customerId = customer.getCustomerId();
			PositionBean position = positionDAO.read(customerId, fundId);
			double share = position.getShares();
			request.setAttribute("share", share);
			
			double inputShare = Double.parseDouble(request.getParameter("amount"));
			if (inputShare < 0) {
				errors.add("Please input a positive number!");
				return "sellFundConfirm.jsp";
			}
			if (positionDAO.remainShare(customerId, fundId, inputShare) < 0) {
				errors.add("You don't have enough share available in this fund!");
				return "sellFundConfirm.jsp";
			}
			
			
//			if (inputShare > share) {
//				errors.add("You don't have enough share available in this fund!");
//				return "sellFundConfirm.jsp";
//			}
			// create a transactionBean
			TransactionBean trans = new TransactionBean();
			trans.setCustomerId(customer.getCustomerId());
			trans.setFundId(fundId);
			trans.setShares(inputShare);
			trans.setTransactionType("SELL");
			trans.setFundName(fund.getName());
			transactionDAO.create(trans);
			
			request.setAttribute("successMessage", "Sell Fund Successfully!");
			return "viewTransaction.do";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (NumberFormatException e) {
			errors.add("Invalid number of share!");
			return "sellFundConfirm.jsp";
		} catch (NullPointerException e) {
			errors.add("You don't have shares in this fund!");
			return "error.jsp";
		}
		
	}

}
