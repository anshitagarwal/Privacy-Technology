package task7;

import javax.servlet.http.HttpServletRequest;

import java.sql.*;


public class AuditorAction extends Action {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/task14";
    static String[] ageGroup = {"0 - 10", "11 - 18", "19 - 25", "25 - 32", "32 - 40", "40 - 50", "50 - 60", "60 - 70", "70 - 80", "90+"}; 
    StringBuffer pw1;
    StringBuffer pw2;
    
    public void performAction() {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,"","");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM finalData where data_sharing <> 'UNENROLLED'";
            ResultSet rs = stmt.executeQuery(sql);

            pw1 = new StringBuffer();
            pw2 = new StringBuffer();
            
            pw1.append("GENDER,BIRTHYEAR,ETHNICITY,STATE,DISEASE_NAME,AD_KEYWORDS,COUPON_CODE,DISEASE_TREATS,CHEMICAL_NAME,MARKETTING_NAME,PRESCRIPTION_PROBABILITY\n");
            pw2.append("FIRSTNAME,LASTNAME,EMAIL,ADDRESS,CITY,ZIP,SSN,GENDER,BIRTHYEAR,ETHNICITY,STATE,DISEASE_NAME,AD_KEYWORDS,COUPON_CODE,DISEASE_TREATS,CHEMICAL_NAME,MARKETTING_NAME,PRESCRIPTION_PROBABILITY\n");
            
            while(rs.next()) {
                String disease_name  = rs.getString("disease_name");
                String disease_treats = rs.getString("disease_treats");
                String chemical_name = rs.getString("chemical_name");
                String marketing_name = rs.getString("marketing_name");
                String perscription_probability = rs.getString("perscription_probability");
                //int insurance_health_id = rs.getInt("insurance_health_id");
                //String plan_number = rs.getString("plan_number");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String gender = rs.getString("gender");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String zip = rs.getString("zip");
                String ethnicity = rs.getString("ethnicity");
                String ssn = rs.getString("ssn");
                String state = rs.getString("state");
                //String grocery_member_id = rs.getString("grocery_member_id");
                String email = rs.getString("email");
                String ad_keywords = rs.getString("ad_keywords");
                String coupon_code = rs.getString("coupon_code");
                String city = rs.getString("city");
                String data_sharing = rs.getString("data_sharing");

                if (data_sharing.equals("NO")) {
                	int age = 2017 - Integer.parseInt(dob.substring(1, 5));
                	String ageString = "";
                	if (age < 11) {
                		ageString = ageGroup[0];
                	} else if (age < 19) {
                		ageString = ageGroup[1];
                	} else if (age < 26) {
                		ageString = ageGroup[2];
                	} else if (age < 33) {
                		ageString = ageGroup[3];
                	} else if (age < 41) {
                		ageString = ageGroup[4];
                	} else if (age < 51) {
                		ageString = ageGroup[5];
                	} else if (age < 61) {
                		ageString = ageGroup[6];
                	} else if (age < 71) {
                		ageString = ageGroup[7];
                	} else if (age < 81) {
                		ageString = ageGroup[8];
                	} else if (age < 91) {
                		ageString = ageGroup[9];
                	} else {
                		ageString = ageGroup[10];
                	}
                	
                	pw1.append(gender + "," + ageString  + "," + ethnicity + "," + state + "," + disease_name + "," + ad_keywords + "," + coupon_code + "," + disease_treats + "," + chemical_name + "," + marketing_name + "," + perscription_probability.substring(0, 4) + "\n");
                } else {
                	pw2.append(firstname + "," + lastname + "," + email  + "," + address + "," + city + "," + zip + "," + ssn + "," + gender + "," + dob + "," + ethnicity + "," + state + "," + disease_name + "," + ad_keywords + "," + coupon_code + "," + disease_treats + "," + chemical_name + "," + marketing_name + "," + perscription_probability + "\n");
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	public AuditorAction() {
		;
	}
	
	@Override
	public String getName() {
		return "auditor.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		String act = request.getParameter("act");
		if (act == null) {
			return "auditor.jsp";
		} else {
			performAction();
			if (act.equals("consent")) {
				return "output_Consent.csv" + pw2.toString() + ".download";
			} else if (act.equals("noconsent")) {
				return "output_NoConsent.csv" + pw1.toString() + ".download";
			}
		}
		return "auditor.jsp";
	}
}
