package task7;

import java.io.IOException;
import java.util.Calendar;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import task7.databeans.AuditorBean;
import task7.model.AuditorDAO;
import task7.model.Model;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init() throws ServletException {
        Model model;
        model = new Model(getServletConfig());
		Action.add(new AuditorAction(model));
 
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
    }
    
    private String performTheAction(HttpServletRequest request) {
//        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
//        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
//        CustomerBean customer = (CustomerBean) session.getAttribute("customer");
        String action = getActionName(servletPath);
        
//        if (employee == null && customer == null) {
//        	return Action.perform("login.do", request);
//        }

        // Let the logged in user run his chosen action
        return Action.perform(action, request);
    }
    
    private void sendToNextPage(String nextPage, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    request.getServletPath());
            return;
        }

        if (nextPage.endsWith(".do")) {
            response.sendRedirect(nextPage);
            return;
        }

        if (nextPage.endsWith(".jsp")) {
            RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
                    + nextPage);
            d.forward(request, response);
            return;
        }

        throw new ServletException(Controller.class.getName()
                + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }
    
    private String getActionName(String path) {
        // We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash + 1);
    }
}
