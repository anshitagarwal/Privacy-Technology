package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCustomerForm extends FormBean {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String cash;
    private String addrLine1;
    private String addrLine2;
    private String city;
    private String state;
    private String zip;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = trimAndConvert(userName, "<>\"");
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = trimAndConvert(firstName, "<>\"");
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = trimAndConvert(lastName, "<>\"");;
    }
    
 
    public String getCash() {
        return cash;
    }
    public void setCash(String cash){
        this.cash = cash; 
    }
    public String getAddrLine1() {
        return addrLine1;
    }
    public void setAddrLine1(String addrLine1){
        this.addrLine1 = addrLine1; 
    }
    public String getAddrLine2() {
        return addrLine2;
    }
    public void setAddrLine2(String addrLine2){
        this.addrLine2 = addrLine2; 
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city){
        this.city = city; 
    }
    public String getState() {
        return state;
    }
    public void setState(String state){
        this.state = state; 
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip){
        this.zip = zip; 
    }
    
    
    @Override
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        if (userName == null || userName.length() == 0) {
            errors.add("userName is required.");
        }
        if (password == null || password.length() == 0) {
            errors.add("password is required.");
        }
        if (firstName == null || firstName.length() == 0) {
            errors.add("firstName is required.");
        }
        if (lastName == null || lastName.length() == 0) {
            errors.add("lastName is required.");
        }
        if (addrLine1 == null || addrLine1.length() == 0) {
            errors.add("Address is required.");
        }
        if (city == null || city.length() == 0) {
            errors.add("City is required.");
        }
        if (state == null || state.length() == 0) {
            errors.add("state is required.");
        }
        if (zip == null || zip.length() == 0) {
            errors.add("ZIP code is required.");
        }
        
        if (city.matches(".*[<>\"].*"))
			errors.add("City may not contain angle brackets or quotes");
        if (state.matches(".*[<>\"].*"))
			errors.add("State may not contain angle brackets or quotes");
        return errors;
        
    }
    
}
