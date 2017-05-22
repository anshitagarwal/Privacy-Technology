package task7;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.FundPriceHistoryBean;
import task7.databeans.OwnedFundBean;
import task7.databeans.PositionBean;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;

public class ViewCustomerAction extends Action {
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
    public ViewCustomerAction(Model model) {
//		customerDAO = model.getCustomerDAO();
//		positionDAO = model.getPositionDAO();
//		fundDAO = model.getFundDAO();
//		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
    }
	@Override
	public String getName() {
		return "viewCustomer.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		if (request.getSession().getAttribute("employee") == null) {
			return "login.do";
		}
		
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			FundBean[] tmp = fundDAO.getFund();
			request.setAttribute("fundList", tmp);
			FundPriceHistoryBean pre = fundPriceHistoryDAO.getNewestPrice(1);
			Date lastDate = null;
			if (pre != null) lastDate = pre.getPriceDate();
			request.setAttribute("lastDate", lastDate);
			
			CustomerBean customer = customerDAO.read(id);
			request.setAttribute("customer", customer);
			PositionBean[] positions = positionDAO.getOwned(customer.getCustomerId());
        	OwnedFundBean[] fundList = new OwnedFundBean[positions.length];
        	for (int i = 0; i < positions.length; i++) {
        		OwnedFundBean ownedFund = new OwnedFundBean();
        		FundBean fund = fundDAO.read(positions[i].getFundId());
        		double lastPrice = fundPriceHistoryDAO.read(positions[i].getFundId(), lastDate).getPrice();
        		ownedFund.setValue(positions[i].getShares() * lastPrice);
        		ownedFund.setFundName(fund.getName());
        		ownedFund.setFundSymbol(fund.getSymbol());
        		ownedFund.setShares(positions[i].getShares());
        		fundList[i] = ownedFund;
        	}
        	request.setAttribute("fundList", fundList);
			return "viewCustomer.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (NumberFormatException e) {
			errors.add("Invalid customer!");
			return "error.jsp";
		}
	}

}
