package task7.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import task7.databeans.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {

	public FundPriceHistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}
	
	public FundPriceHistoryBean[] getFundPriceHistorys() throws RollbackException {
		FundPriceHistoryBean[] beans = match();
		return beans;
	}
	
	// search by fund_id and select the newest price date
	public FundPriceHistoryBean getNewestPrice(int fundId) throws RollbackException {
		try {
			Transaction.begin();
			FundPriceHistoryBean[] res = match(MatchArg.equals("fundId", fundId));
			if (res.length == 0) {
				return null;
			} else if (res.length == 1) {
				return res[0];
			}
			int index = 0;
			for (int i = 1; i < res.length; i++) {
				if (res[i].getPriceDate().after(res[index].getPriceDate())) {
					index = i;
				}
			}
			Transaction.commit();
			return res[index];
		} finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
	}

	// get unique set of fundIds
	public Integer[] getUniqueFundIds(FundPriceHistoryBean[] beans) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < beans.length; i++) {
			int fundId = beans[i].getFundId();
			if (!set.contains(fundId)) {
				set.add(fundId);
			}
		}
		
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			Integer id = it.next();
			tmp.add(id);
		}
		return (Integer[])tmp.toArray();
	}
	
	public FundPriceHistoryBean[] getFundPriceHistorys(int id) throws RollbackException {
		FundPriceHistoryBean[] beans = match(MatchArg.equals("fundId", id));
		return beans;
	}
}
