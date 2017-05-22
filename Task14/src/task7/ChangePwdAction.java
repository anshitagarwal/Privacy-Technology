package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.formbeans.ChangePwdForm;
import task7.model.CustomerDAO;
import task7.model.Model;


public class ChangePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	private CustomerDAO customerDAO;

	public ChangePwdAction(Model model) {
//		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "change-pwd.do";
	}

	public String perform(HttpServletRequest request) {
		
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		if (request.getSession().getAttribute("customer") == null) {
			errors.add("Please login as a customer first");
			return "login.do";
		}

		try {
			// Set up user list for nav bar
			request.setAttribute("customerList", customerDAO.getCustomers());

			// Load the form parameters into a form bean
			ChangePwdForm form = formBeanFactory.create(request);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "change_pwd.jsp";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "change_pwd.jsp";
			}
			
			HttpSession session = request.getSession();
			CustomerBean user = (CustomerBean) session.getAttribute("customer");

			// Change the password
			CustomerBean updatedUser = customerDAO.setPassword(user.getCustomerId(), form.getNewPassword());
			
			session.setAttribute("customer", updatedUser);
			request.setAttribute("message", "Password changed for " + user.getFirstName());
			return "successPwdChange.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}

}
