package cc.noj.stufftoget.formbeans;

/**
 * The LoginForm.  This includes the users email address and password as
 * well as buttons.
 * 
 * The button could either be Login or Register.  
 * 
 * @author Manoj Dayaram <mdayaram@andrew.cmu.edu>
 * @date February 25th, 2010
 * @class 15-437
 */

import org.mybeans.form.FormBean;
import java.util.ArrayList;
import java.util.List;

public class LoginForm extends FormBean{
    private String userEmail;
    private String password;
    private String button;
    private String from;
    
    public String getFrom(){
    	return from;
    }
    
    public void setFrom(String from){
    	this.from = trimAndConvert(from, "<>\"");
    }
    
    public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = trimAndConvert(userEmail, "<>\"");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = trimAndConvert(password, "<>\"");
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = trimAndConvert(button, "<>\"");
	}
	
	public String trimAndConvert(String s, String delim){
		if(s == null)
			return "";
		
		return super.trimAndConvert(s, delim);
	}

	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (!isUserEmailValid(errors)) 
        	errors.add("Invalid email address entered.");
        if (!isPasswordValid(errors)) 
        	errors.add("Invalid password, must be at least one character.");
        
        if (button == null) 
        	errors.add("Button is required");
        
        if (from != null && from.length() >= 255)
        	errors.add("URL from field is over 255 characters long.");

        if (errors.size() > 0) return errors;

        if (!button.equalsIgnoreCase("Login")) 
        	errors.add("Invalid button");
        if (userEmail.matches("<>\"")) {
        	errors.add("User email may not contain angle brackets or quotes");
        	userEmail = trimAndConvert(userEmail, "<>\"");
        }

        return errors;
    }
	
	private boolean isUserEmailValid(List<String> errors){
		if (userEmail == null || userEmail.isEmpty())
			return false;

		if(userEmail.length() >= 255)
			errors.add("Email is over 255 characters long.");
		
		
		String[] email = userEmail.split("@");
		
		return email.length == 2 && email[0].length() > 0 
				&& email[1].length() > 0;
	}
	
	private boolean isPasswordValid(List<String> errors){
		if(password == null)
			return false;
		
		if(password.length() >= 255)
			errors.add("Password is over 255 characters long.");
		
		return password != null && password.length() > 0;
	}
}

