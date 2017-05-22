package task7.formbeans;

import java.util.ArrayList;
import java.util.List;
import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean {
	private String amount;
	//private String confirmAmount;
	
	//public String getConfirmAmount() { return confirmAmount; }
	public String getAmount()     { return amount;     }

	//public void setConfirmAmount(String s) { confirmAmount = s; }
	public void setAmount(String s)     { amount = s; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (amount == null || amount.length() == 0) {
			errors.add("Amount is required");
		}

		
		if (amount != null && amount.length() != 0) {
			try {
				double temp = Double.parseDouble(amount);
				if(temp == 0) {
					errors.add("Amount entered cannot be zero");
				}
				if(temp < 0) {
					errors.add("Amount entered cannot be negative");
				}
			} catch (NumberFormatException e) {
				errors.add("Amount entered is not a number");
			}
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		String[] sep = amount.split("\\.");
		if (sep[0].length() >= 7) {
			errors.add("Amount entered is greater than $999,999. Please enter a lower amount.");
		}
		
		return errors;

	}
}
