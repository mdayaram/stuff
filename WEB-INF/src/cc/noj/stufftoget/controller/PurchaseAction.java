package cc.noj.stufftoget.controller;

import javax.servlet.http.HttpServletRequest;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.ItemBean;
import cc.noj.stufftoget.databeans.UserBean;
import cc.noj.stufftoget.formbeans.IdForm;
import cc.noj.stufftoget.model.ItemDAO;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.UserDAO;


public class PurchaseAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private ItemDAO itemDAO;
	private UserDAO  userDAO;
	
    public PurchaseAction(Model model) {
    	itemDAO = model.getItemDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "purchase.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			UserBean user;
			user = (UserBean) request.getSession(false).getAttribute("user");
			
	    	IdForm form = formBeanFactory.create(request);
	    	
	    	ItemBean item;
	    	if(!form.isPresent()) {
	    		errors.add("No item selected for purchase.");
	    		return "error.jsp";
	    	}
	    	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
        
    		int id = form.getIdAsInt();
    		item = itemDAO.lookup(id);
    		if (item == null) {
    			errors.add("No item with id="+id);
    			return "error.jsp";
    		}
    		
    		item.setPurchasedById(user.getId());
    		item.setDatePurchased(new Date(System.currentTimeMillis()));
    		item.setStatus(ItemBean.STATUS_PENDING);
    		item = itemDAO.update(item);
    		user = userDAO.addPoints(user.getId(), item.getPoints());
    		
    		String msg = "<p>You have purchased the item \"" + 
    				item.getName()+"\" and have earned " + item.getPoints() + 
    				" points by doing so! Congratulations!</p><br><br><p>" +
    				"Upon the purchase of your item, please mail it to the " +
    				"following address:</p><br><p>SMC 5101<br>5032 Forbes Ave" +
    				"<br>Pittsburgh, PA 15289<br>USA</p><br><br><p>Upon the " +
    				"receipt of your item, the site will be updated with an " +
    				"action shot of me using it!</p>";

    		request.getSession().setAttribute("user", user);
    		request.setAttribute("message",msg);
    		request.setAttribute("title","Thank you for your purchase!");

            return "message.jsp";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}

