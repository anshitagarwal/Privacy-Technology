package task7.databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId")
public class FundBean {
	private int fundId;
	private String name;
	private String symbol;
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
}
