package task7.model;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import task7.databeans.AuditorBean;

//public class AuditorDAO extends GenericDAO<AuditorBean> {
//
//	public AuditorDAO(ConnectionPool cp, String tableName) throws DAOException {
//		super(AuditorBean.class, tableName, cp);
//	}
//	
//	public AuditorBean[] query() throws RollbackException {
//		AuditorBean[] infos = match();
//		return infos;
//	}
//}

public class AuditorDAO {
	private List<Connection> connectionPool = new ArrayList<>();
	private String jdbcDriver;
    private String jdbcURL;
    private String tableName;
    
    public AuditorDAO(String jdbcDriver, String jdbcURL, String tableName) {
    	this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;
        this.tableName = tableName;

//        if (!tableExists())
//            createTable();
    }
    
    private synchronized Connection getConnection() {
        if (connectionPool.size() > 0) {
            return connectionPool.remove(connectionPool.size() - 1);
        }

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return null;
    }

    private synchronized void releaseConnection(Connection con) {
        connectionPool.add(con);
    }
    
    
    private boolean tableExists() throws Exception {
        Connection con = null;
        try {
            con = getConnection();
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet rs = metaData.getTables(null, null, tableName, null);

            boolean answer = rs.next();

            rs.close();
            releaseConnection(con);

            return answer;

        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new Exception(e);
        }
    }
    
    public AuditorBean[] getItems() {
    	Connection connection = null;
    	try {
    		connection = getConnection();
    		Statement stmt = connection.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
    		List<AuditorBean> list = new ArrayList<>();
    		while (rs.next()) {
    			AuditorBean bean = new AuditorBean();
    			bean.setDiseaseName(rs.getString("diseaseName"));
    			bean.setDiseaseTreats(rs.getString("diseaseTreats"));
    			bean.setChemicalName(rs.getString("chemicalName"));
    			bean.setMarketingName(rs.getString("marketingName"));
    			bean.setPerscriptionProbability(rs.getString("perscriptionProbability"));
    			bean.setInsuranceHealthId(rs.getInt("insuranceHealthId"));
    			bean.setPlanNumber(rs.getString("planNumber"));
    			bean.setFirstname(rs.getString("firstname"));
    			bean.setLastname(rs.getString("lastname"));
    			bean.setGender(rs.getString("gender"));
    			bean.setDob(rs.getString("dob"));
    			bean.setAddress(rs.getString("address"));
    			bean.setZip(rs.getString("zip"));
    			bean.setEthnicity(rs.getString("ethnicity"));
    			bean.setSsn(rs.getString("ssn"));
    			bean.setState(rs.getString("state"));
    			bean.setGroceryMemberId(rs.getString("groceryMemberId"));
    			bean.setCreditCard(rs.getString("creditCard"));
    			bean.setAdKeywords(rs.getString("adKeywords"));
    			bean.setCouponCode(rs.getString("couponCode"));
    			bean.setId(rs.getInt("id"));
    			bean.setDataSharing(rs.getString("dataSharing"));
    			list.add(bean);
    		}
    		stmt.close();
    		releaseConnection(connection);
    		return list.toArray(new AuditorBean[list.size()]);
    	} catch (SQLException e) {
            try {
                if (connection != null)
                	connection.close();
            } catch (SQLException e2) { /* ignore */
            }
        }
		return null;
    }
}











