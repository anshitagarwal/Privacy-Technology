package task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import task7.databeans.CustomerBean;


public class CustomerDAO extends GenericDAO<CustomerBean>  {

	public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerBean.class, tableName, cp);
	}
	public CustomerBean[] getCustomers() throws RollbackException {
		CustomerBean[] customers = match();
		return customers;
	}
	@Override
    public void create(CustomerBean customerBean) throws RollbackException {
        try {
            Transaction.begin();
            CustomerBean[] res = match(MatchArg.equals("userName", customerBean.getUserName()));
            if (res.length > 0) {
                throw new RollbackException("Please choose another User Name. This one already exists");
            }
            super.create(customerBean);
            Transaction.commit();
        } 
        finally {
            if (Transaction.isActive()) {
                Transaction.rollback();
            }
            }
        }
	public CustomerBean read(String username) throws RollbackException {
		try {
			return this.match(MatchArg.equals("userName", username))[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	public CustomerBean setPassword(int id, String password) throws RollbackException {
        try {
            Transaction.begin();
            CustomerBean dbUser = read(id);
         
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
	public void resetPassword(int id) throws RollbackException {
		try {
			Transaction.begin();
			CustomerBean customer = this.read(id);
			customer.setPassword("123456");
			this.update(customer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
	public double remainAvailable(int customerId, double amount) throws RollbackException {
		try {
			Transaction.begin();
			CustomerBean customer = this.read(customerId);
			double remain = customer.getAvailable() - amount;
			if (remain >= 0) {
				customer.setAvailable(remain);
				this.update(customer);
			}
			Transaction.commit();
			return remain;
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
	public double addRemainAvailable(int customerId, double amount) throws RollbackException {
		try {
			Transaction.begin();
			CustomerBean customer = this.read(customerId);
			double remain = customer.getAvailable() + amount;
		
				customer.setAvailable(remain);
				this.update(customer);
			
			Transaction.commit();
			return remain;
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
	
}
