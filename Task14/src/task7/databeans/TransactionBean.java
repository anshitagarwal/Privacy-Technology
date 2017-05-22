package task7.databeans;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class TransactionBean {
	private int transactionId;
	private int customerId;
	private int fundId;
	private Date executeDate;
	private double shares;
	private String fundName;
	private double price;
	
	//GenericDAO doesn't support this type
	//private TransactionType transationType;
	
	private String transactionType;
	private double amount;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String s) {
		this.fundName = s;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public Date getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public double getShares() {
		return shares;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double s) {
		this.amount = s;
	}
	
	
}
