package cc.noj.stufftoget.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cc.noj.stufftoget.databeans.PhotoBean;
import cc.noj.stufftoget.formbeans.IdForm;
import cc.noj.stufftoget.model.Model;
import cc.noj.stufftoget.model.PhotoDAO;


/**
 * This action looks up the photo bean by "id" and then passes it
 * (via request attribute) to the ImageServlet.  See also the mapping
 * of /image in the web.xml file.
 * 
 * We need to use a servlet instead of a JSP for the "view" of the image
 * because we need to send back the image bytes and not HTML.
 */
public class ImageAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private PhotoDAO photoDAO;

    public ImageAction(Model model) {
    	photoDAO = model.getPhotoDAO();
	}

    public String getName() { return "image.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	IdForm form = formBeanFactory.create(request);
        	
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "error.jsp";
            }

        	PhotoBean p = photoDAO.lookup(form.getIdAsInt());
        	if(p == null || p.getContentType() == null){
        		return "default.jpg";
        	}
    		request.setAttribute("photo",p);
    		
    		// Note: "/image" is mapped (in the web.xml file) to the ImageServlet
    		// which looks at the "photo" request attribute and sends back the bytes.
    		// If there is no "photo" attribute, it sends back HTTP Error 404 (resource not found).
    		return "image";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
