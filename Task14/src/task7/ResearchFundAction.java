package task7;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import task7.databeans.FundBean;
import task7.databeans.ResearchFundBean;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class ResearchFundAction extends Action {
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ResearchFundAction(Model model) {
//		fundDAO = model.getFundDAO();
//		transactionDAO = model.getTransactionDAO();
//		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	@Override
	public String getName() {
		return "researchFund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		if (request.getSession().getAttribute("customer") == null) {
			errors.add("Please login as a customer first");
			return "login.do";
		}
		
		try {
			FundBean[] funds = fundDAO.getFund();
			ResearchFundBean[] historys = new ResearchFundBean[funds.length];
			for (int i = 0; i < historys.length; i++) {
				ResearchFundBean h = new ResearchFundBean();
				h.setFundId(funds[i].getFundId());
				h.setFundName(funds[i].getName());
				h.setFundSymbol(funds[i].getSymbol());
				h.setHistory(fundPriceHistoryDAO.getFundPriceHistorys(funds[i].getFundId()));
				historys[i] = h;
			}
			request.setAttribute("historyList", historys);
			return "researchFund.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}

}
