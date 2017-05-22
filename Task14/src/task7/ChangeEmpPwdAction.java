package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.formbeans.ChangePwdForm;
import task7.model.CustomerDAO;
import task7.model.EmployeeDAO;
import task7.model.Model;


public class ChangeEmpPwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	private EmployeeDAO employeeDAO;

	public ChangeEmpPwdAction(Model model) {
//		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "changeemppwd.do";
	}

	public String perform(HttpServletRequest request) {
		if (request.getSession().getAttribute("employee") == null) {
			return "login.do";
		}
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// Set up user list for nav bar
			request.setAttribute("employeeList", employeeDAO.getEmployees());

			// Load the form parameters into a form bean
			ChangePwdForm form = formBeanFactory.create(request);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "changeemppwd.jsp";
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "changeemppwd.jsp";
			}
			
			HttpSession session = request.getSession();
			EmployeeBean user = (EmployeeBean) session.getAttribute("employee");

			// Change the password
			EmployeeBean updatedUser = employeeDAO.setPassword(user.getUserName(), form.getNewPassword());
			
			session.setAttribute("employee", updatedUser);
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
