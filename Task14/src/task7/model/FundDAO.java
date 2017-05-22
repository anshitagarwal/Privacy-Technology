package task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import task7.databeans.FundBean;

public class FundDAO extends GenericDAO<FundBean> {
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}
	public FundBean[] getFund() throws RollbackException {
		FundBean[] fund = match();
		return fund;
	}
	
	@Override
	public void create(FundBean fundBean) throws RollbackException {
		try {
			Transaction.begin();
			FundBean[] res = match(MatchArg.equals("name", fundBean.getName()));

			res = match(MatchArg.equals("symbol", fundBean.getSymbol()));
			if (res.length > 0) {
				throw new RollbackException("Please choose another Name or Symbol! This one is alreadly existed");
			}
			super.create(fundBean);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	

}
