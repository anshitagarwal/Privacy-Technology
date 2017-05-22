package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.TransactionBean;
import task7.formbeans.DepositCheckForm;
import task7.formbeans.RequestCheckForm;
import task7.model.CustomerDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class RequestCheckAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory.getInstance(RequestCheckForm.class);
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	
	public RequestCheckAction(Model model) {
//	       customerDAO = model.getCustomerDAO();
//	       transactionDAO = model.getTransactionDAO();
	    }
	    
	    @Override
	    public String getName() {
	        return "requestcheck.do";
	    }

	    @Override
	    public String perform(HttpServletRequest request) {
	        List<String> errors = new ArrayList<String>();
	       
			HttpSession session = request.getSession();
			if (request.getSession().getAttribute("customer") == null) {
				errors.add("Please login as a customer first");
				return "login.do";
			}
			
			try {
				request.setAttribute("errors", errors);
		        
			     
		        String successMessage = new String();
				CustomerBean user = (CustomerBean) session.getAttribute("customer");
				CustomerBean customer = customerDAO.read(user.getCustomerId());
				request.getSession().setAttribute("customer", customer);

	            RequestCheckForm form = formBeanFactory.create(request);
	            request.setAttribute("form", form);
	           
	            
	            if (!form.isPresent()) {
	                return "requestcheck.jsp";
	            }
	            errors.addAll(form.getValidationErrors());
	            if (errors.size() != 0) {
	                return "requestcheck.jsp";
	            }
	    		//String userName = request.getParameter("userName");
	            double amount = Double.parseDouble(request.getParameter("amount"));
	            if(customerDAO.remainAvailable(user.getCustomerId(),amount) < 0){
	            	errors.add("You don't have enough money available in your account!");
					request.getSession().setAttribute("customer", customer);
					return "requestcheck.jsp";
	            }

//	            user.setAvailable(user.getAvailable() - Double.parseDouble(form.getAmount()));
//		        customerDAO.update(user);
	            TransactionBean bean = new TransactionBean();
	            bean.setCustomerId(user.getCustomerId());
	            bean.setExecuteDate(null);
	            bean.setTransactionType("REQUEST");
	            bean.setAmount(Double.parseDouble(form.getAmount()));
	           
	            transactionDAO.create(bean);
	            successMessage = "Your Check has been Requested Successfully";
	            customer = customerDAO.read(user.getCustomerId());
	            request.getSession().setAttribute("customer", customer);
	            request.setAttribute("successMessage", successMessage);
	            return "viewTransaction.do";
			} catch (RollbackException e) {
				errors.add(e.toString());
				return "error.jsp";
			} catch (FormBeanException e) {
				errors.add("No such fund!");
				return "error.jsp";
			}
	       
	        
            
	        
	       
	        
	    }
	
}
