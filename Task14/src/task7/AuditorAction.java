package task7;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import task7.databeans.AuditorBean;
import task7.model.AuditorDAO;
import task7.model.Model;

public class AuditorAction extends Action {

	private AuditorDAO auditorDAO;
	
	public AuditorAction(Model model) {
		auditorDAO = model.getAuditorDAO();
	}
	
	@Override
	public String getName() {
		return "auditor.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		AuditorBean[] records = auditorDAO.getItems();
		System.out.println(records.length);
		request.setAttribute("records", records);
		return "auditor.jsp";
	}
	
}
