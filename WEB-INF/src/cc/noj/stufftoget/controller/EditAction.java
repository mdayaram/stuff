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
import cc.noj.stufftoget.formbeans.IdForm;
import cc.noj.stufftoget.formbeans.ItemForm;
import cc.noj.stufftoget.model.ItemDAO;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.PhotoDAO;

public class EditAction extends Action {
	private FormBeanFactory<ItemForm> itemFormBeanFactory;

	private ItemDAO itemDAO;
	private PhotoDAO photoDAO;
	
	public EditAction(Model model) {
		itemFormBeanFactory = FormBeanFactory.getInstance(ItemForm.class);
    	itemDAO = model.getItemDAO();
    	photoDAO = model.getPhotoDAO();
	}

	public String getName() { 
		return "edit.do"; 
	}

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {

			ItemForm itemForm = itemFormBeanFactory.create(request);
			if(!itemForm.isPresent()){
				errors.add("No item selected for editing.");
				return "error.jsp";
			}
			
			if(itemForm.getId() != null && itemForm.getName() == null){
				ItemBean item = itemDAO.lookup(itemForm.getIdAsInt());
				request.setAttribute("item", item);
				return "edit.jsp";
			}
			// validate data from form
	        errors.addAll(itemForm.getValidationErrors());
	        if (errors.size() > 0) return "error.jsp";

	        ItemBean item = itemDAO.lookup(itemForm.getIdAsInt());
	        if(item == null) {
	        	errors.add("No such item with given id.");
	        	return "error.jsp";
	        }
	        
	        // create item from data
	        FileProperty fileProp = itemForm.getImgFile();
	        if(fileProp != null && fileProp.getBytes().length > 0) {
	        	PhotoBean photo = new PhotoBean();
	        	photo.setBytes(fileProp.getBytes());
	        	photo.setContentType(fileProp.getContentType());
	        	photo = photoDAO.create(photo);
	        	item.setImageId(photo.getId());
	        }
	        fileProp = itemForm.getActionFile();
	        if(fileProp != null && fileProp.getBytes().length > 0){
	        	PhotoBean photo = new PhotoBean();
	        	photo.setBytes(fileProp.getBytes());
	        	photo.setContentType(fileProp.getContentType());
	        	photo = photoDAO.create(photo);
	        	item.setActionImageId(photo.getId());
	        }
	        item.setComment(itemForm.getComment());
	        item.setDateAdded(new Date(System.currentTimeMillis()));
	        item.setName(itemForm.getName());
	        item.setPrice(itemForm.getPriceAsDouble());
	        try {
	        	item.setPoints(itemForm.getPointsAsInt());
	        } catch (Exception e) {
	        	item.setPoints((int)Math.round(item.getPrice()));
	        }
	        item.setRank(itemForm.getRankAsInt());
	        item.setStatus(itemForm.getStatusAsInt());
	        item.setTags(itemForm.getTags());
	        item.setUrl(itemForm.getUrl());
	        
	        item = itemDAO.update(item);
	        
	        return request.getContextPath() + "/view.do?id="+item.getId();
	 	} catch (DAOException e) {
	 		e.printStackTrace();
			errors.add(e.getMessage());
			return "error.jsp";
	 	} catch (FormBeanException e) {
	 		if(e.getMessage().startsWith("Files must be send using multipart")){
	 			FormBeanFactory<IdForm> idFormFactory = FormBeanFactory.getInstance(IdForm.class);
	 			IdForm idForm = null;
	 			try {
					idForm = idFormFactory.create(request);
				} catch (FormBeanException e2) {
					errors.add(e.getMessage());
					return "error.jsp";
				}
	 			int id = idForm.getIdAsInt();
	 			ItemBean item = null;
				try {
					item = itemDAO.lookup(id);
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				request.setAttribute("item", item);
	 			return "edit.jsp"; //throws "no multipart enctype error when no form is present.
	 		}
	 		e.printStackTrace();
			errors.add(e.getMessage());
			return "error.jsp";
		}
    }
}
