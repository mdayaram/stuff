package cc.noj.stufftoget.controller;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.ItemBean;
import cc.noj.stufftoget.databeans.PrizesBean;
import cc.noj.stufftoget.databeans.RedeemedPrizesBean;
import cc.noj.stufftoget.databeans.UserBean;
import cc.noj.stufftoget.formbeans.IdForm;
import cc.noj.stufftoget.model.ItemDAO;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.PrizesDAO;
import cc.noj.stufftoget.model.RedeemedPrizesDAO;
import cc.noj.stufftoget.model.UserDAO;


public class RedeemAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private PrizesDAO prizesDAO;
	private RedeemedPrizesDAO redeemedDAO;
	private UserDAO userDAO;
	
    public RedeemAction(Model model) {
    	prizesDAO = model.getPrizesDAO();
    	userDAO  = model.getUserDAO();
    	redeemedDAO = model.getRedeemedDAO();
	}

    public String getName() { return "redeem.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			UserBean user;
			user = (UserBean) request.getSession(false).getAttribute("user");
			
	    	IdForm form = formBeanFactory.create(request);
	    	
	    	PrizesBean prize;
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
    		prize = prizesDAO.lookup(id);
    		if (prize == null) {
    			errors.add("No item with id="+id);
    			return "error.jsp";
    		}
    		
    		if (user.getPoints() < prize.getPoints()){
    			errors.add("You do not have enough points for this prize.");
    			return "error.jsp";
    		}
    		
    		RedeemedPrizesBean redeemed = new RedeemedPrizesBean();
    		redeemed.setPrizeID(prize.getId());
    		redeemed.setUserID(user.getId());
    		redeemedDAO.create(redeemed);
    		
    		user = userDAO.removePoints(user.getId(), prize.getPoints());
    		request.getSession().setAttribute("user", user);
    		
    		String msg = "<p>You have redeemed the prize \"" + 
    				prize.getName()+"\"! <br><br>Expect Noj to pay you a visit to " +
    				"hand deliver your prize!  Congratulations!</p>";

    		request.setAttribute("message",msg);
    		request.setAttribute("title","Congratulations on your Prize!");

    		sendMail(prize, user);
    		
            return "message.jsp";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
    
    private void sendMail(PrizesBean prize, UserBean user){
    	// sad face....need a mail server...oh poop.
    }
}

