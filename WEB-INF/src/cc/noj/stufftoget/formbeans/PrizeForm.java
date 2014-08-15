package cc.noj.stufftoget.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class PrizeForm extends FormBean {
	
	private String points;
	private String name;
	private String action;

	public String getPoints() {
		return points;
	}
	
	public int getPointsAsInt() {
		try {
			return Integer.parseInt(points);
		} catch (NumberFormatException e){
			e.printStackTrace();
			return -1;
		}
	}

	public void setPoints(String points) {
		this.points = trimAndConvert(points);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = trimAndConvert(name);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = trimAndConvert(action);
	}
	
	public String trimAndConvert(String s){
		if(s == null)
			return "";
		
		String delim = "<>\"";
		return super.trimAndConvert(s, delim);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		

		if(points == null || points.isEmpty())
			errors.add("Points are required.");
		
		if(points.length() >= 255)
			errors.add("Points has more than 255 digits.");
			
		try {
			int p = Integer.parseInt(points);
			if (p <= 0) 
				errors.add("Can't have a <= 0 points worth prize.");
		} catch (NumberFormatException e) {
			errors.add("points not an integer:  "+e.getMessage());
		}
		
		if (name == null || name.isEmpty())
			errors.add("Name is a required field.");
		
		if (name.length() >= 255)
			errors.add("Name is over 255 characters long.");
		
		if (action == null || action.isEmpty())
			errors.add("Action is a required field.");
		
		if (action.length() >= 255)
			errors.add("Action is over 255 characters long.");
		
		return errors;
	}
}
