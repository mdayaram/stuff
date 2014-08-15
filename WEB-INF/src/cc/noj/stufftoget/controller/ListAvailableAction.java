package cc.noj.stufftoget.controller;

/**
 * Lists the bookmarks of a given user.  The IdForm is used to retrieve the
 * ID of the user whose bookmarks we should display.
 * 
 * @author Manoj Dayaram <mdayaram@andrew.cmu.edu>
 * @date February 25th, 2010
 * @class 15-437
 */

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.ItemBean;
import cc.noj.stufftoget.formbeans.IdForm;
import cc.noj.stufftoget.model.ItemDAO;
import cc.noj.stufftoget.model.Model;


public class ListAvailableAction extends Action {

	private ItemDAO itemDAO;

    public ListAvailableAction(Model model) {
    	itemDAO = model.getItemDAO();
	}

    public String getName() { 
    	return "available.do"; 
    }

    public String perform(HttpServletRequest request) {
        // Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			ItemBean[] items = itemDAO.lookupByStatus(ItemBean.STATUS_AVAILABLE);
	        request.setAttribute("itemsList",items);
	        return "available.jsp";
        } catch (DAOException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "error.jsp";
        } 
    }
}
