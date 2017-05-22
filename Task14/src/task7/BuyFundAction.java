package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;

import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class BuyFundAction extends Action {
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public BuyFundAction(Model model) {
////		fundDAO = model.getFundDAO();
////		transactionDAO = model.getTransactionDAO();
//		customerDAO = model.getCustomerDAO();
	}
	@Override
	public String getName() {
		return "buyFund.do";
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
			CustomerBean oldcustomer = (CustomerBean) request.getSession().getAttribute("customer");
			CustomerBean customer = customerDAO.read(oldcustomer.getCustomerId());
			request.getSession().setAttribute("customer", customer);
			if (request.getParameter("buy") != null) {
				FundBean fund = fundDAO.read(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("fund", fund);
				return "buyFundConfirm.jsp";
			}
			FundBean[] funds = fundDAO.getFund();
			request.setAttribute("fundList", funds);
			return "buyFund.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (NumberFormatException e) {
			errors.add("Invalid fund id!");
			return "error.jsp";
		}
	}

}
