package task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import task7.databeans.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean> {

	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TransactionBean.class, tableName, cp);
	}

	public TransactionBean[] getTransactions() throws RollbackException {
		TransactionBean[] transactions = match();
		return transactions;
	}
	
	// search the transactionBean according to the customer id 
	public TransactionBean[] read(int customerId) throws RollbackException {
		TransactionBean[] res = match(MatchArg.equals("customerId", customerId));
		return res;
	}
	
	public TransactionBean[] getPending() throws RollbackException {
		TransactionBean[] res = match(MatchArg.equals("executeDate", null));
		return res;
	}
	
}
