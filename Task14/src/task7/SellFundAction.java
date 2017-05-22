package task7;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position;

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

public class SellFundAction extends Action {

	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	
	
	public SellFundAction(Model model) {
//		fundDAO = model.getFundDAO();
//		positionDAO = model.getPositionDAO();
	}
	
	@Override
	public String getName() {
		return "sellFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		if (request.getSession().getAttribute("customer") == null) {
			errors.add("Please login as a customer first");
			return "login.do";
		}
		
		try {
			
			if (request.getParameter("sell") != null) {
				FundBean fund = fundDAO.read(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("fund", fund);
				request.setAttribute("id", fund.getFundId());
				CustomerBean customer = (CustomerBean) request.getSession().getAttribute("customer");
				int customerId = customer.getCustomerId();
				PositionBean position = positionDAO.read(customerId, fund.getFundId());
				double share = position.getShares();
				request.setAttribute("share", share);
				//request.setAttribute("share", share);
				return "sellFundConfirm.jsp";
			}
			
			// find all the funds this user owned.
			CustomerBean customer = (CustomerBean) request.getSession(false).getAttribute("customer"); 
			PositionBean[] positions = positionDAO.getOwned(customer.getCustomerId()); 
			FundBean[] ownedFunds = new FundBean[positions.length];
			for (int i = 0; i < positions.length; i++) {
				ownedFunds[i] = fundDAO.read(positions[i].getFundId());
			}
			request.setAttribute("ownedFundsList", ownedFunds);
			return "sellFund.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (NumberFormatException e) {
			errors.add("Invalid fund id!");
			return "error.jsp";
		}
	}
}
