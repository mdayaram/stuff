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
import cc.noj.stufftoget.databeans.PrizesBean;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.PrizesDAO;


public class ListPrizesAction extends Action {

	private PrizesDAO prizesDAO;

    public ListPrizesAction(Model model) {
    	prizesDAO = model.getPrizesDAO();
	}

    public String getName() { 
    	return "prizes.do"; 
    }

    public String perform(HttpServletRequest request) {
        // Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			PrizesBean[] prizes = prizesDAO.list();
	        request.setAttribute("prizesList",prizes);
	        return "prizes.jsp";
        } catch (DAOException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "error.jsp";
        } 
    }
}
