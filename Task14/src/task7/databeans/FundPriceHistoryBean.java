package task7.databeans;


import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId,priceDate")
public class FundPriceHistoryBean {
	private int fundId;
	private Date priceDate;
	private double price;
	public int getFundId() {
		return fundId;
	}
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}
	public Date getPriceDate() {
		return priceDate;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
