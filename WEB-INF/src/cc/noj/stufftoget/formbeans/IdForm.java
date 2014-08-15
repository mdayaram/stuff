package cc.noj.stufftoget.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class IdForm extends FormBean {
	
	private String id;

	public String getId() { 
		return id;    
	}
	
	public int getIdAsInt() {
		try {
			return Integer.parseInt(id);
		} catch (NumberFormatException e) {
			// call getValidationErrors() to detect this
			return -1;
		}
	}
	public void setId(String id) { 
		this.id = trimAndConvert(id, "<>\""); 
	}
	
	public String trimAndConvert(String s, String delim){
		if(s == null)
			return "";
		
		return super.trimAndConvert(s, delim);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (id == null || id.length() == 0) {
			errors.add("Id is required");
			return errors;
		}
		
		if (id.length() >= 255){
			errors.add("Id is longer than 255 digits.");
		}

		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			errors.add("Id is not an integer");
		}
		
		return errors;
	}
}
