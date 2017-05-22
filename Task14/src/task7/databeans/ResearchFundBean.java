package task7.databeans;

public class ResearchFundBean {
	private int fundId;
	private String fundName;
	private String fundSymbol;
	private FundPriceHistoryBean[] history;
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getFundSymbol() {
		return fundSymbol;
	}
	public void setFundSymbol(String fundSymbol) {
		this.fundSymbol = fundSymbol;
	}
	public FundPriceHistoryBean[] getHistory() {
		return history;
	}
	public void setHistory(FundPriceHistoryBean[] history) {
		this.history = history;
	}
}
