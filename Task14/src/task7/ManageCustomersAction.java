package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import task7.databeans.CustomerBean;
import task7.model.CustomerDAO;
import task7.model.Model;

public class ManageCustomersAction extends Action {
	private CustomerDAO customerDAO;
	
	public ManageCustomersAction(Model model) {
//		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "manageCustomer.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		if (request.getSession().getAttribute("employee") == null) {
			return "login.do";
		}
		
		try {
			CustomerBean[] customerList = customerDAO.getCustomers();
			request.setAttribute("customerList", customerList);
			
			return "manageCustomer.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}

	}
	
	
}
