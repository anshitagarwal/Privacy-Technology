package task7.model;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

public class Model {
//	private EmployeeDAO employeeDAO;
//	private CustomerDAO customerDAO;
//	private FundDAO fundDAO;
//	private TransactionDAO transactionDAO;
//	private PositionDAO positionDAO;
//	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	private AuditorDAO auditorDAO;

	public Model(ServletConfig config) throws ServletException {
		String jdbcDriver = config.getInitParameter("jdbcDriverName");
		String jdbcURL    = config.getInitParameter("jdbcURL");
		

		ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
		
//			auditorDAO = new AuditorDAO(pool, "finalData"); 
		auditorDAO = new AuditorDAO(jdbcDriver, jdbcURL, "finalData");
	}
	
	public AuditorDAO getAuditorDAO() {
		return auditorDAO;
	}
}
