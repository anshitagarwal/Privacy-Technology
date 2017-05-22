package task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(EmployeeBean.class, tableName, cp);
	}
	
	public EmployeeBean[] getEmployees() throws RollbackException {
		EmployeeBean[] employees = match();
		return employees;
	}
	public EmployeeBean setPassword(String name, String password) throws RollbackException {
        try {
            Transaction.begin();
            EmployeeBean dbUser = read(name);
         
            if (dbUser == null) {
                throw new RollbackException("User no longer exists");
            }
            dbUser.setPassword(password);

            update(dbUser);
            Transaction.commit();
            
            return dbUser;
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
	
	@Override
	public void create(EmployeeBean employeeBean) throws RollbackException {
		try {
			Transaction.begin();
			String usrn = employeeBean.getUserName();
			EmployeeBean res = this.read(usrn);
			if (res != null) {
				throw new RollbackException("Please choose another User Name! This one is alreadly existed");
			}
			super.create(employeeBean);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
}
