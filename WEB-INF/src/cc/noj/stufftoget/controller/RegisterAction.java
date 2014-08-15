package cc.noj.stufftoget.controller;

/**
 * Adds the user to our database.  The information for the new user is
 * obtained from the RegisterForm.  If the form is not present in the
 * request, the user is redirected to the login page so that we can
 * obtain the email and password.
 * 
 * Once registered, the user is redirected to the manage action.
 * 
 * @author Manoj Dayaram <mdayaram@andrew.cmu.edu>
 * @date February 25th, 2010
 * @class 15-437
 */

import java.util.ArrayList;
import java.util.HashMap;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.UserBean;
import cc.noj.stufftoget.formbeans.RegisterForm;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.UserDAO;

public class RegisterAction extends Action {
	
	private FormBeanFactory<RegisterForm> formBeanFactory;
	private UserDAO userDAO;
	
	public RegisterAction(Model model) {
		formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
		userDAO = model.getUserDAO();
	}

	public String getName() { 
		return "register.do"; 
	}

    public String perform(HttpServletRequest request) {
    	// set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	        // If no params were passed, return with no errors so that the 
	        // register form will be presented.
	        if (!form.isPresent()) {
	        	return "register.jsp";
	        }
	
	        // validate any errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "register.jsp";
	        }
	
	        // create the new user given the info on the form
	        UserBean user = new UserBean();
	    	user.setEmailAddress(form.getUserEmail());
	    	user.setFirstName(form.getFirstName());
	    	user.setLastName(form.getLastName());
	    	user.setPasswordHash(UserBean.hash(form.getPassword()));
	    	user.setPoints(0);
	    	user = userDAO.create(user);
        
			// Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
	        HashMap<Integer, UserBean> userMap =(HashMap<Integer,UserBean>)session.getAttribute("userMap");
	        userMap.put(user.getId(), user);
	        
	        // redirect him to the manage page
			String webapp = request.getContextPath();
			return webapp + "/view.do";
        } catch (DAOException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (FormBeanException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "register.jsp";
        }
    }
}
