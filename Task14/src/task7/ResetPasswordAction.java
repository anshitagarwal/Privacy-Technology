package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import task7.databeans.CustomerBean;
import task7.model.CustomerDAO;
import task7.model.Model;

public class ResetPasswordAction extends Action {
	private CustomerDAO customerDAO;
    public ResetPasswordAction(Model model) {
//		customerDAO = model.getCustomerDAO();
    }
	@Override
	public String getName() {
		return "resetPwd.do";
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
			customerDAO.resetPassword(id);
			CustomerBean customer = customerDAO.read(id);
			request.setAttribute("customer", customer);
			return "resetSuccess.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (NumberFormatException e) {
			errors.add("Invalid customer!");
			return "error.jsp";
		}
	}

}
