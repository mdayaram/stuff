package cc.noj.stufftoget.controller;

import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.PrizesBean;
import cc.noj.stufftoget.formbeans.PrizeForm;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.PrizesDAO;

public class AddPrizeAction extends Action {
	private FormBeanFactory<PrizeForm> formBeanFactory;

	private PrizesDAO prizeDAO;
	
	public AddPrizeAction(Model model) {
		formBeanFactory = FormBeanFactory.getInstance(PrizeForm.class);
    	prizeDAO = model.getPrizesDAO();
	}

	public String getName() { 
		return "addprize.do"; 
	}

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			PrizeForm form = formBeanFactory.create(request);
			if(!form.isPresent()){
				return "addprize.jsp";
			}
			
			// validate data from form
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) return "error.jsp";

	        PrizesBean prize = new PrizesBean();
	        prize.setAction(form.getAction());
	        prize.setName(form.getName());
	        prize.setPoints(form.getPointsAsInt());
	        
	        if(prizeDAO.exists(prize.getName())){
	        	prizeDAO.updateByName(prize);
	        } else {
	        	prize = prizeDAO.create(prize);
	        }
	        	        
	        return request.getContextPath() + "/prizes.do";
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
