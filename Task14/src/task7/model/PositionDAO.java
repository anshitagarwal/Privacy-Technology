package task7.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import task7.databeans.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean> {
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PositionBean.class, tableName, cp);
	}
	public PositionBean[] getPositions() throws RollbackException {
		PositionBean[] positions = match();
		return positions;
	}
	public PositionBean[] getOwned(int id) throws RollbackException {
		PositionBean[] positions = match(MatchArg.equals("customerId", id));
		return positions;
	}
	
	// better way??
	public PositionBean getSpecificShare(int customerId, int fundId) throws RollbackException {
		try {
			Transaction.begin();
			PositionBean[] positions = match(MatchArg.equals("customerId", customerId));
			
			int index = 0;
			for (int i = 0; i < positions.length; i++) {
				if (positions[i].getFundId() == fundId) {
					index = i;
				}
			}
			Transaction.commit();
			return positions[index];
		} finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
	}
	
	
	public double remainShare(int customerId, int fundId, double inputShare) throws RollbackException {
		try {
			Transaction.begin();
			PositionBean position = this.read(customerId, fundId);
			double remain = position.getShares() - inputShare;
			if (remain >= 0) {
				position.setShares(remain);
				this.update(position);
			}
			Transaction.commit();
			return remain;
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
}
