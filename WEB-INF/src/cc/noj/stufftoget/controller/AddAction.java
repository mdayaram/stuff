package cc.noj.stufftoget.controller;


import java.sql.Date;
import java.util.ArrayList;


import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.ItemBean;
import cc.noj.stufftoget.databeans.PhotoBean;
import cc.noj.stufftoget.databeans.UserBean;
import cc.noj.stufftoget.formbeans.ItemForm;
import cc.noj.stufftoget.model.ItemDAO;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.PhotoDAO;

public class AddAction extends Action {
	private FormBeanFactory<ItemForm> formBeanFactory;

	private ItemDAO itemDAO;
	private PhotoDAO photoDAO;
	
	public AddAction(Model model) {
		formBeanFactory = FormBeanFactory.getInstance(ItemForm.class);
    	itemDAO = model.getItemDAO();
    	photoDAO = model.getPhotoDAO();
	}

	public String getName() { 
		return "add.do"; 
	}

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			// get logged in user
			UserBean user;
			user = (UserBean) request.getSession(false).getAttribute("user");

			ItemForm form = formBeanFactory.create(request);
			if(!form.isPresent()){
				return "add.jsp";
			}
			
			// validate data from form
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) return "error.jsp";

	        // create item from data
	        FileProperty fileProp = form.getImgFile();
	        PhotoBean photo = new PhotoBean();
	        photo.setBytes(fileProp.getBytes());
	        photo.setContentType(fileProp.getContentType());
	        photo = photoDAO.create(photo);

	        ItemBean item = new ItemBean();
	        item.setImageId(photo.getId());
	        item.setAddedById(user.getId());
	        item.setComment(form.getComment());
	        item.setDateAdded(new Date(System.currentTimeMillis()));
	        item.setName(form.getName());
	        item.setPrice(form.getPriceAsDouble());
	        try {
	        	item.setPoints(form.getPointsAsInt());
	        } catch (Exception e) {
	        	item.setPoints((int)Math.round(item.getPrice()));
	        }
	        item.setRank(ItemBean.RANK_LOW);
	        item.setStatus(ItemBean.STATUS_AVAILABLE);
	        item.setTags(form.getTags());
	        item.setUrl(form.getUrl());
	        
	        item = itemDAO.create(item);
	        
	        return request.getContextPath() + "/view.do?id="+item.getId();
	 	} catch (DAOException e) {
	 		e.printStackTrace();
			errors.add(e.getMessage());
			return "error.jsp";
	 	} catch (FormBeanException e) {
	 		if(e.getMessage().startsWith("Files must be send using multipart")){
	 			return "add.jsp"; //throws "no multipart enctype error when no form is present.
	 		}
	 		e.printStackTrace();
			errors.add(e.getMessage());
			return "error.jsp";
		}
    }
}
