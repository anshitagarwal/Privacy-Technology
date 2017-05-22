package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.databeans.TransactionBean;
import task7.model.Model;
import task7.model.TransactionDAO;

public class viewTransactionAction extends Action {

	private TransactionDAO transactionDAO;
	
	public viewTransactionAction(Model model) {
//		transactionDAO = model.getTransactionDAO();
	}
	@Override
	public String getName() {
		return "viewTransaction.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
	    HttpSession session = request.getSession(true);
	    String s = null;
	    EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
	    CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		List<String> errors = new ArrayList<String>();
		if (employee != null){
		int id = Integer.parseInt(request.getParameter("id"));
		String userName = request.getParameter("userName");
		try {
			TransactionBean[] transactionList = transactionDAO.read(id);
			request.setAttribute("transactionList", transactionList);
			request.setAttribute("userName", userName);
			s = "viewTransaction.jsp";
			return s;
			}
		 catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
			
		}
	} else if (customer != null){
	    try {
            TransactionBean[] transactionList = transactionDAO.read(customer.getCustomerId());
            request.setAttribute("transactionList", transactionList);
            request.setAttribute("userName", customer.getUserName());
            s = "viewCustomerTransaction.jsp";
            return s;
            }
         catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
            
         }
	        } else s = "login.do";
		return s;
	} 
	
}
