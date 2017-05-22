package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String username;
	private String password;
	private String loginType;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = trimAndConvert(username, "<>\"");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = trimAndConvert(password, "<>\"");
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public boolean isPresent() {
        return loginType != null;
    }
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (username == null || username.length() == 0)
            errors.add("User name is required");
        if (password == null || password.length() == 0)
            errors.add("Password is required");
        

        return errors;
    }
}
