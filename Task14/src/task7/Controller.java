package task7;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init() throws ServletException {
        Action.add(new AuditorAction());
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
        String servletPath = request.getServletPath();
        String action = getActionName(servletPath);
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

        if (nextPage.endsWith(".download")) {
        	OutputStream out;
    		try {
    			String filename = nextPage.substring(0, nextPage.indexOf(".csv") + 4);
    			nextPage = nextPage.substring(nextPage.indexOf(".csv") + 4);
    			response.setHeader( "Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
    			out = response.getOutputStream();
    			nextPage = nextPage.substring(0, nextPage.length() - 9);
    			byte[] buffer = nextPage.getBytes();
    			out.write(buffer);
    			out.flush();

    		} catch (IOException e) {
    			e.printStackTrace();
    		}
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
