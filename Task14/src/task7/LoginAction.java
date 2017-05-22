package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.formbeans.LoginForm;
import task7.model.CustomerDAO;
import task7.model.EmployeeDAO;
import task7.model.Model;

public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	public LoginAction(Model model) {
//		employeeDAO = model.getEmployeeDAO();
//		customerDAO = model.getCustomerDAO();
    }
	@Override
	public String getName() {
		return "login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			LoginForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "login.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "login.jsp";
			}
			
			if (form.getLoginType().equals("Login as an employee")) {
				EmployeeBean employee = new EmployeeBean();
				employee = employeeDAO.read(form.getUsername());
				if (employee == null) {
					errors.add("No employee with that user name.");
					return "login.jsp";
				}
				if (!form.getPassword().equals(employee.getPassword())) {
					errors.add("Incorrect password");
					return "login.jsp";
				}
				request.getSession().setAttribute("employee", employee);
			}
			if (form.getLoginType().equals("Login as a customer")) {
				CustomerBean customer = new CustomerBean();
				customer = customerDAO.read(form.getUsername());//to be revised
				if (customer == null) {
					errors.add("No customer with that user name.");
					return "login.jsp";
				}
				if (!form.getPassword().equals(customer.getPassword())) {
					errors.add("Incorrect password");
					return "login.jsp";
				}
				request.getSession().setAttribute("customer", customer);
			}
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
		return "home.do";
	}
	
}
