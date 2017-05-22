package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.TransactionBean;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class BuyConfirmAction extends Action {
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public BuyConfirmAction(Model model) {
//		fundDAO = model.getFundDAO();
//		transactionDAO = model.getTransactionDAO();
//		customerDAO = model.getCustomerDAO();
	}
	@Override
	public String getName() {
		return "buyConfirm.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		//Validate login.
		if (request.getSession().getAttribute("customer") == null) {
			errors.add("Please login as a customer first");
			return "login.do";
		}
		
		//Validate hidden field.
		String confirmId = request.getParameter("confirmid");
		int id;
		try {
			id = Integer.parseInt(confirmId);
		} catch (NumberFormatException e) {
			errors.add("Invalid fund id!");
			return "error.jsp";
		}
		
		try {
			FundBean fund = fundDAO.read(id);
			request.setAttribute("fund", fund);
			TransactionBean trans = new TransactionBean();
			CustomerBean oldcustomer = (CustomerBean) request.getSession().getAttribute("customer");
			CustomerBean customer = customerDAO.read(oldcustomer.getCustomerId());
			double amount = Double.parseDouble(request.getParameter("amount"));
			
			//Limit input amount.
			String[] sep = request.getParameter("amount").split("\\.");
			if (sep[0].length() >= 7) {
				errors.add("Amount entered is greater than $999,999. Please enter a lower amount.");
				return "buyFundConfirm.jsp";
			}
			if (customerDAO.remainAvailable(customer.getCustomerId(), amount) < 0) {
				errors.add("You don't have enough money available in your account!");
				request.getSession().setAttribute("customer", customer);
				return "buyFundConfirm.jsp";
			}
			
			//Pass all validations. Add the buy fund transaction into the queue.
			trans.setCustomerId(customer.getCustomerId());
			trans.setFundId(fund.getFundId());
			trans.setTransactionType("BUY");
			trans.setAmount(amount);
			trans.setFundName(fund.getName());
			transactionDAO.create(trans);
			request.getSession().setAttribute("customer", customer);
			request.setAttribute("successMessage", "Buy fund successfully!");
			return "viewTransaction.do";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (NumberFormatException e) {
			errors.add("Invalid dollar amount!");
			return "buyFundConfirm.jsp";
		} catch (NullPointerException e) {
			errors.add("No such fund!");
			return "error.jsp";
		}
	}

}
