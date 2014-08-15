package cc.noj.stufftoget.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.ItemBean;
import cc.noj.stufftoget.formbeans.IdForm;
import cc.noj.stufftoget.model.ItemDAO;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.UserDAO;


public class ViewAction extends Action {

	private static final Random rand = new Random();
	
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private ItemDAO itemDAO;
	private UserDAO  userDAO;
	
    public ViewAction(Model model) {
    	itemDAO = model.getItemDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "view.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

	    	IdForm form = formBeanFactory.create(request);
	    	
	    	ItemBean item;
	    	if(!form.isPresent()) {
	    		ItemBean[] items = itemDAO.lookupByStatus(ItemBean.STATUS_AVAILABLE);
	    		if(items.length <= 0)
	    			return "view.jsp";
	    		
	    		item = items[rand.nextInt(items.length)];
	    	} else {
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
	    	}
    		request.setAttribute("item",item);
    		request.setAttribute("title",item.getName());

            return "view.jsp";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}

