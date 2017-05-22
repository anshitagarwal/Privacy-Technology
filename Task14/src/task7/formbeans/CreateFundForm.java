package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
	private String name;
	private String symbol;
	
	public void setName(String a){
		this.name = trimAndConvert(a, "<>\"");
	}
	public void setSymbol(String a){
		this.symbol = trimAndConvert(a, "<>\"");
	}
	public String getName(){
		return name;
	}
	public String getSymbol(){
		return symbol;
	}
	@Override
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (name == null || name.length() == 0) {
			errors.add("Name is required.");
		}
		if (symbol == null || symbol.length() == 0) {
			errors.add("symbol is required.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
			if (name.matches(".*[<>\"].*"))
				errors.add("Name may not contain angle brackets or quotes");
			
			if (symbol.matches(".*[<>\"].*"))
				errors.add("symbol may not contain angle brackets or quotes");
		return errors;
		
	}

}
