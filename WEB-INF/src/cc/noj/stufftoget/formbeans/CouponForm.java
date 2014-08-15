package cc.noj.stufftoget.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CouponForm extends FormBean {
	
	private String code;

	public String getCode() { 
		return code;    
	}
	
	public void setCode(String code) { 
		this.code = trimAndConvert(code, "<>\""); 
	}
	
	public String trimAndConvert(String s, String delim){
		if(s == null)
			return "";
		
		return super.trimAndConvert(s, delim);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (code == null || code.length() == 0) {
			errors.add("Coupon code is required");
		}
		
		if(code.length() >= 255){
			errors.add("Coupon code is longer than 255 characters.");
		}
		
		return errors;
	}
}
