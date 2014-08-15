package cc.noj.stufftoget.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {

    private String userEmail;
    private String password;
    private String confirmedPassword;
    private String firstName;
    private String lastName;
    private String button;
    
    
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
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = trimAndConvert(confirmedPassword, "<>\"");
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
		this.lastName = trimAndConvert(lastName, "<>\"");
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
        if (!isFirstNameValid(errors))
        	errors.add("First names must be at least one character.");
        if (!isLastNameValid(errors))
        	errors.add("Last names must be at least one character.");

        if (!password.equals(confirmedPassword))
        	errors.add("Password does not match previous password given.");
        
        if (button == null) 
        	errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!button.equals("Register")) 
        	errors.add("Invalid button");
        
        String invalidChars = "<>\"";
        
        if (userEmail.matches(invalidChars)) {
        	errors.add("User email may not contain angle brackets or quotes");
        	userEmail = trimAndConvert(userEmail, "<>\"");
        }
        
        if (firstName.matches(invalidChars)){
        	errors.add("First Name may not contain angle brackets or quotes");
        	firstName = trimAndConvert(firstName, "<>\"");
        }
        
        if (lastName.matches(invalidChars)){
        	errors.add("Last Name may not contain angle brackets or quotes");
        	lastName = trimAndConvert(lastName, "<>\"");
        }

        return errors;
    }

	private boolean isUserEmailValid( List<String> errors){
		if (userEmail == null || userEmail.isEmpty())
			return false;
		
		if (userEmail.length() >= 255)
			errors.add("Email address is over 255 characters long.");
		
		String[] email = userEmail.split("@");
		
		return email.length == 2 && email[0].length() > 0 
				&& email[1].length() > 0;
	}
	
	private boolean isPasswordValid( List<String> errors){
		if (password == null || confirmedPassword == null
				|| password.isEmpty() || confirmedPassword.isEmpty())
			return false;

		if (password.length() >= 255 || confirmedPassword.length() >= 255)
			errors.add("Password is over 255 characters long.");
		
		return password.length() > 0 && confirmedPassword.length() > 0;
	}
	
	private boolean isFirstNameValid( List<String> errors){

		if (firstName != null && firstName.length() >= 255)
			errors.add("First Name is over 255 characters long.");
		
		return firstName != null && firstName.length() > 0;
	}
	
	private boolean isLastNameValid( List<String> errors){

		if (lastName != null && lastName.length() >= 255)
			errors.add("Last Name is over 255 characters long.");
		
		return lastName != null && lastName.length() > 0;
	}
}
