package task7;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;


public class AuditorAction extends Action {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/task14";
    
    public void performAction() {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,"","");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM finalData";
            ResultSet rs = stmt.executeQuery(sql);
            
            PrintWriter pw1 = new PrintWriter(new File("output_NoConsent.csv"), "UTF-8");
            PrintWriter pw2 = new PrintWriter(new File("output_Consent.csv"), "UTF-8");
            PrintWriter pw3 = new PrintWriter(new File("original_file.csv"), "UTF-8");

            pw1.print("GENDER,BIRTHYEAR,ETHNICITY,STATE,DISEASE_NAME,AD_KEYWORDS,COUPON_CODE,DISEASE_TREATS,CHEMICAL_NAME,MARKETTING_NAME,PRESCRIPTION_PROBABILITY\n");
            pw2.print("FIRSTNAME,LASTNAME,ADDRESS,CITY,ZIP,SSN,GENDER,BIRTHYEAR,ETHNICITY,STATE,DISEASE_NAME,AD_KEYWORDS,COUPON_CODE,DISEASE_TREATS,CHEMICAL_NAME,MARKETTING_NAME,PRESCRIPTION_PROBABILITY\n");
            pw3.print("FIRSTNAME,LASTNAME,ADDRESS,CITY,ZIP,SSN,GENDER,BIRTHYEAR,ETHNICITY,STATE,DISEASE_NAME,AD_KEYWORDS,COUPON_CODE,DISEASE_TREATS,CHEMICAL_NAME,MARKETTING_NAME,PRESCRIPTION_PROBABILITY\n");

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
                //String credit_card = rs.getString("credit_card");
                String ad_keywords = rs.getString("ad_keywords");
                String coupon_code = rs.getString("coupon_code");
                String city = rs.getString("city");
                String data_sharing = rs.getString("data_sharing");

                if (data_sharing.equals("NO")) {
                    pw1.print(gender + "," + dob.substring(1, 5)  + "," + ethnicity + "," + state + "," + disease_name + "," + ad_keywords + "," + coupon_code + "," + disease_treats + "," + chemical_name + "," + marketing_name + "," + perscription_probability + "\n");
                } else {
                    pw2.print(firstname + "," + lastname + "," + address + "," + city + "," + zip + "," + ssn + "," + gender + "," + dob + "," + ethnicity + "," + state + "," + disease_name + "," + ad_keywords + "," + coupon_code + "," + disease_treats + "," + chemical_name + "," + marketing_name + "," + perscription_probability + "\n");
                }
                pw3.print(firstname + "," + lastname + "," + address + "," + city + "," + zip + "," + ssn + "," + gender + "," + dob + "," + ethnicity + "," + state + "," + disease_name + "," + ad_keywords + "," + coupon_code + "," + disease_treats + "," + chemical_name + "," + marketing_name + "," + perscription_probability + "\n");
            }
            rs.close();
            stmt.close();
            conn.close();
            pw1.close();
            pw2.close();
            pw3.close();
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
		performAction();
		return "auditor.jsp";
	}
	
}
