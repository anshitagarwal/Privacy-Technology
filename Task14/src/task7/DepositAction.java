package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.TransactionBean;
import task7.formbeans.DepositCheckForm;
import task7.model.CustomerDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class DepositAction extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(DepositCheckForm.class);
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	
	public DepositAction(Model model) {
//	       customerDAO = model.getCustomerDAO();
//	       transactionDAO = model.getTransactionDAO();
	    }
	    
	    @Override
	    public String getName() {
	        return "depositcheck.do";
	    }

	    @Override
	    public String perform(HttpServletRequest request) {
	        List<String> errors = new ArrayList<String>();
	        if (request.getSession().getAttribute("employee") == null) {
				return "login.do";
			}
	        int id = Integer.parseInt(request.getParameter("id"));
	        request.setAttribute("errors", errors);
	        String successMessage = new String();
	        try {
	            DepositCheckForm form = formBeanFactory.create(request);
	            request.setAttribute("form", form);
	            CustomerBean customer = customerDAO.read(id);
	            request.setAttribute("customer", customer);
	            if (!form.isPresent()) {
	                return "depositcheck.jsp";
	            }
	            errors.addAll(form.getValidationErrors());
	            if (errors.size() != 0) {
	                return "depositcheck.jsp";
	            }
	    		//String userName = request.getParameter("userName");
	            customer.setAvailable(customer.getAvailable() + Double.parseDouble(form.getAmount()));
	            customerDAO.update(customer);
	            TransactionBean bean = new TransactionBean();
	            bean.setCustomerId(id);
	          
	            bean.setExecuteDate(null);
	            bean.setTransactionType("DEPOSIT");
	       
	            bean.setAmount(Double.parseDouble(form.getAmount()));
	            //add validation to check if customer with id "id" exists
	            transactionDAO.create(bean);
	            successMessage = "Deposit Check request has been processed successfully";
	            request.setAttribute("successMessage", successMessage);
	            
	            //request.setAttribute("customerList", customerDAO.getCustomers());
	            return "depositcheck.jsp";
	            
	        } catch (RollbackException e) {
	            errors.add(e.getMessage());
	            return "depositcheck.jsp";
	        } catch (FormBeanException e) {
	            errors.add(e.getMessage());
	            return "depositcheck.jsp";
	        }
	        
	    }
	    
	}
