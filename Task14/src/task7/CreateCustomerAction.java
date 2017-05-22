package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.formbeans.CreateCustomerForm;
import task7.model.*;

public class CreateCustomerAction extends Action {
    private FormBeanFactory<CreateCustomerForm> formBeanFactory = FormBeanFactory.getInstance(CreateCustomerForm.class);
    private CustomerDAO customerDAO;
    
    public CreateCustomerAction(Model model) {
//       customerDAO = model.getCustomerDAO();
    }
    
    @Override
    public String getName() {
        return "createCustomer.do";
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
            CreateCustomerForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            request.setAttribute("customerList", customerDAO.getCustomers());
            
            
            if (!form.isPresent()) {
                return "createCustomer.jsp";
            }
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "createCustomer.jsp";
            }
                
            CustomerBean bean = new CustomerBean();
            bean.setUserName(form.getUserName());
            bean.setPassword(form.getPassword());
            bean.setFirstName(form.getFirstName());
            bean.setLastName(form.getLastName());
            bean.setAddrLine1(form.getAddrLine1());
            bean.setAddrLine2(form.getAddrLine2());
           
            bean.setState(form.getState());
            bean.setZip(form.getZip());
            bean.setCity(form.getCity());
            customerDAO.create(bean);
            successMessage = "Customer Added Successfully";
            request.setAttribute("successMessage", successMessage);
            
            request.setAttribute("customerList", customerDAO.getCustomers());
            return "createCustomer.jsp";
            
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "createCustomer.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "createCustomer.jsp";
        }
        
    }
    
}
