package cc.noj.stufftoget.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.UserBean;
import cc.noj.stufftoget.formbeans.LoginForm;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.UserDAO;

public class LoginAction extends Action {
	
	private FormBeanFactory<LoginForm> formBeanFactory;
	private UserDAO userDAO;

	public LoginAction(Model model) {
		formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
		userDAO = model.getUserDAO();
	}

	public String getName() { 
		return "login.do"; 
	}
    
    public String perform(HttpServletRequest request) {
    	// set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	// create our login form and set it as an attribute for persistence
	    	LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that 
	        // the form will be presented (we assume for the first time).
	        if (!form.isPresent() || (form.getFrom() != null && form.getUserEmail() == null)) {
	            return "login.jsp";
	        }

	        // Redirect to error if any errors present
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }
	        
	        // Look up the user, if he doesn't exist, show login screen again
	        UserBean user = userDAO.lookup(form.getUserEmail());
	        if (user == null) {
	            errors.add("User with email '"
	            		+form.getUserEmail()+"' not found.");
	            return "login.jsp";
	        }

	        // Check the password, if it doesn't match, show login screen again
	        if (!user.checkPassword(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("user",user);
	        
	        String dest;
	        if(form.getFrom() == null || form.getFrom().equals("")){
	        	dest = "/view.do";
	        } else {
	        	dest = "/"+form.getFrom();
	        }
	        
			String webapp = request.getContextPath();
			return webapp + dest;
        } catch (DAOException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
