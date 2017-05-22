package task7;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.model.Model;



public class LogoutAction extends Action {

    public LogoutAction(Model model) {
    }

    public String getName() {
        return "logout.do";
    }

    public String perform(HttpServletRequest request) {
    	HttpSession session = request.getSession(true);
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        CustomerBean customer = (CustomerBean) session.getAttribute("customer");
        if (employee != null) session.setAttribute("employee", null);;
        if (customer != null) session.setAttribute("customer", null);;

        return "login.jsp";
    }
}

