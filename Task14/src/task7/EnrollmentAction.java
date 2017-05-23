package task7;

import javax.servlet.http.HttpServletRequest;

public class EnrollmentAction extends Action {

	@Override
	public String getName() {
		return "enrollment.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return "enrollment.jsp";
	}

}
