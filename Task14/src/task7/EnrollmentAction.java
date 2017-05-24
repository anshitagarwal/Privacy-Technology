package task7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class EnrollmentAction extends Action {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/task14?useSSL=false";
	@Override
	public String getName() {
		return "enrollment.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		String registerbutton = request.getParameter("registerbutton");
		String registerbuttonupdate = request.getParameter("registerbuttonupdate");
		//if (registerbutton != null || registerbuttonupdate != null) {
		if (registerbutton != null) {
			String firstname = request.getParameter("firstname");
			firstname = firstname == null? firstname: firstname.trim();
			String lastname = request.getParameter("lastname");
			lastname = lastname == null? lastname : lastname.trim();
			String dob = request.getParameter("dob");
			dob = dob == null? dob : dob.trim();
			String email = request.getParameter("email");
			email = email == null? email : email.trim();
			String consent = request.getParameter("consent");
			consent = consent == null? consent : consent.trim();

			String sql = "";
			if (consent.equals("YES")) {
				sql = "UPDATE finalData SET email='" + email + "', data_sharing = 'YES' WHERE firstname='\"" + firstname + "\"' AND lastname='\"" + lastname + "\"' AND dob='\"" + dob + "\"'";
			} else {
				sql = "UPDATE finalData SET email='" + email + "', data_sharing = 'NO' WHERE firstname='\"" + firstname + "\"' AND lastname='\"" + lastname + "\"' AND dob='\"" + dob + "\"'";
			}
			System.out.println(sql);

			Connection conn = null;
	        Statement stmt = null;
	        try{
	            Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection(DB_URL,"","");
	            stmt = conn.createStatement();
	            stmt.executeUpdate(sql);

	            if (registerbuttonupdate != null) {
	            	String address = request.getParameter("address").trim();
	            	String city = request.getParameter("city").trim();
	            	String state = request.getParameter("state").trim();
	            	String zip = request.getParameter("zip").trim();
	            	sql = "UPDATE finalData SET address='" + address + "', zip = '" + zip + "', city = '" + city + "', state = '" + state + "' WHERE firstname='\"" + firstname + "\"' AND lastname='\"" + lastname + "\"' AND dob='\"" + dob + "\"'";
	            	System.out.println(sql);
	            	stmt.executeUpdate(sql);
				}
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
		}
		
		return "enrollment.jsp";
	}

}
