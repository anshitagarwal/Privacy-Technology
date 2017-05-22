package task7;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.databeans.FundBean;
import task7.databeans.FundPriceHistoryBean;
import task7.databeans.OwnedFundBean;
import task7.databeans.PositionBean;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;

public class HomeAction extends Action {
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private CustomerDAO customerDAO;
	public HomeAction(Model model) {
//		positionDAO = model.getPositionDAO();
//		fundDAO = model.getFundDAO();
//		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
//		customerDAO = model.getCustomerDAO();
    }
	@Override
	public String getName() {
		return "home.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		HttpSession session = request.getSession(true);
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        CustomerBean customer = (CustomerBean) session.getAttribute("customer");
        if (employee != null) return "employeeHome.jsp";
        if (customer != null) {
        	try {
        		CustomerBean oldcustomer = (CustomerBean) request.getSession().getAttribute("customer");
    			customer = customerDAO.read(oldcustomer.getCustomerId());
    			request.getSession().setAttribute("customer", customer);
        		FundBean[] tmp = fundDAO.getFund();
    			request.setAttribute("fundList", tmp);
    			FundPriceHistoryBean pre = fundPriceHistoryDAO.getNewestPrice(1);
    			Date lastDate = null;
    			if (pre != null) lastDate = pre.getPriceDate();
    			request.setAttribute("lastDate", lastDate);
    			
        		PositionBean[] positions = positionDAO.getOwned(customer.getCustomerId());
            	OwnedFundBean[] fundList = new OwnedFundBean[positions.length];
            	for (int i = 0; i < positions.length; i++) {
            		OwnedFundBean ownedFund = new OwnedFundBean();
            		int id = positions[i].getFundId();
            		FundBean fund = fundDAO.read(id);
            		double lastPrice = fundPriceHistoryDAO.read(id, lastDate).getPrice();
            		ownedFund.setValue(positions[i].getShares() * lastPrice);
            		ownedFund.setFundName(fund.getName());
            		ownedFund.setFundSymbol(fund.getSymbol());
            		ownedFund.setShares(positions[i].getShares());
            		fundList[i] = ownedFund;
            	}
            	request.setAttribute("fundList", fundList);
            	return "customerHome.jsp";
        	} catch (RollbackException e) {
    			errors.add(e.toString());
    			return "error.jsp";
    		}
        }
        return "login.do";
	}

}
