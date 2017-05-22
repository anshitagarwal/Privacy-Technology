package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.FundBean;
import task7.formbeans.CreateFundForm;

import task7.model.FundDAO;
import task7.model.Model;

public class CreateFundAction extends Action {
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);
	private FundDAO fundDAO;
	
	public CreateFundAction(Model model) {
//		fundDAO = model.getFundDAO();
	}
	
	@Override
	public String getName() {
		return "createFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
	List<String> errors = new ArrayList<String>();
	request.setAttribute("errors", errors);
	if (request.getSession().getAttribute("employee") == null) {
		return "login.do";
	}
	String successMessage = new String();
	
	try {
		CreateFundForm form = formBeanFactory.create(request);
		request.setAttribute("form", form);
		request.setAttribute("fundList", fundDAO.getFund());
		
		
		if (!form.isPresent()) {
			return "createFund.jsp";
		}
		errors.addAll(form.getValidationErrors());
		if (errors.size() != 0) {
			return "createFund.jsp";
		}
			
		FundBean bean = new FundBean();
		bean.setName(form.getName());
		bean.setSymbol(form.getSymbol());
		fundDAO.create(bean);
		successMessage = "Fund Added Successfully";
		request.setAttribute("successMessage", successMessage);
		
		request.setAttribute("fundList", fundDAO.getFund());
		return "createFund.jsp";
		
	} catch (RollbackException e) {
		errors.add(e.getMessage());
		return "createFund.jsp";
	} catch (FormBeanException e) {
		errors.add(e.getMessage());
		return "createFund.jsp";
	}
	
	
}

}
