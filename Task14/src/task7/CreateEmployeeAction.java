package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.EmployeeBean;
import task7.formbeans.CreateEmployeeForm;
import task7.model.*;

public class CreateEmployeeAction extends Action {
	private FormBeanFactory<CreateEmployeeForm> formBeanFactory = FormBeanFactory.getInstance(CreateEmployeeForm.class);
	private EmployeeDAO employeeDAO;
	
	public CreateEmployeeAction(Model model) {
//		employeeDAO = model.getEmployeeDAO();
	}
	
	@Override
	public String getName() {
		return "createEmployee.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		if (request.getSession().getAttribute("employee") == null) {
			return "login.do";
		}
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		String successMessage = new String();
		
		try {
			CreateEmployeeForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			request.setAttribute("employeeList", employeeDAO.getEmployees());
			
			
			if (!form.isPresent()) {
				return "createEmployee.jsp";
			}
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "createEmployee.jsp";
			}
				
			EmployeeBean bean = new EmployeeBean();
			bean.setUserName(form.getUserName());
			bean.setPassword(form.getPassword());
			bean.setFirstName(form.getFirstName());
			bean.setLastName(form.getLastName());
			employeeDAO.create(bean);
			successMessage = "Add an Employee Successfully";
			request.setAttribute("successMessage", successMessage);
			
			request.setAttribute("employeeList", employeeDAO.getEmployees());
			return "createEmployee.jsp";
			
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "createEmployee.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "createEmployee.jsp";
		}
		
	}
	
}
