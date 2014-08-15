package cc.noj.stufftoget.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

import cc.noj.stufftoget.databeans.ItemBean;

public class ItemForm extends FormBean {

	public static final int FILE_MAX_LENGTH = 1024 * 1024;
	
	private String id;
	private String name;
	private String price;
	private String points;
	private String url;
	private FileProperty imgFile;
	private FileProperty actionFile;
	private String rank;
	private String comment;
	private String tags;
    private String button;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = trimAndConvert(id);
	}
	public int getIdAsInt() {
		return Integer.parseInt(id);
	}
    public FileProperty getActionFile() {
		return actionFile;
	}
	public void setActionFile(FileProperty actionFile) {
		this.actionFile = actionFile;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = trimAndConvert(status);
	}
    public int getStatusAsInt() {
    	return Integer.parseInt(status);
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = trimAndConvert(name);
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = trimAndConvert(price);
	}
	public double getPriceAsDouble() {
		return Double.parseDouble(price);
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = trimAndConvert(points);
	}
	public int getPointsAsInt() {
		return Integer.parseInt(points);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = trimAndConvert(url);
	}
	public FileProperty getImgFile() {
		return imgFile;
	}
	public void setImgFile(FileProperty imgFile) {
		this.imgFile = imgFile;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = trimAndConvert(rank);
	}
	public int getRankAsInt() {
		return Integer.parseInt(rank);
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = trimAndConvert(comment);
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = trimAndConvert(tags);
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = trimAndConvert(button);
	}
	
	public String trimAndConvert(String s){
		if(s == null)
			return "";
		
		String delim = "<>\"";
		return super.trimAndConvert(s, delim);
	}
    
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (name == null || name.length() == 0)
        	errors.add("Name is required.");
        else if (name.length() >= 255)
        	errors.add("Name is longer than 255 characters.");
       
        if(comment == null || comment.length() == 0)
        	errors.add("A comment is required.");
        else if (comment.length() >= 255)
        	errors.add("Comment is longer than 255 characters.");
        
        if (tags == null || tags.length() == 0)
        	errors.add("Tags are required.");
        else if (tags.length() >= 255)
        	errors.add("Tags are longer than 255 characters.");
        
        if (!isRankValid(errors)) 
        	errors.add("Rank entered is invalid.");
        if (!isStatusValid(errors)) 
        	errors.add("Status entered is invalid.");        
        if (!isURLValid(errors))
        	errors.add("URL entered is invalid.");
        if (!isPointsValid(errors))
        	errors.add("Points entered are invalid.");
        if (!isPriceValid(errors))
        	errors.add("Price entered is invalid.");
        if (!isFileSizesValid(errors))
        	errors.add("File sizes too large.");

        if (button == null) 
        	errors.add("Button is required");

        if (errors.size() > 0) return errors;

        return errors;
    }
	
	private boolean isRankValid(List<String> errors) {
		if(rank == null || rank.isEmpty())
			return true; //probably using a form that doesn't have rank
		
		if(rank.length() >= 255)
			errors.add("Rank is over 255 characters long.");
		
		int r = -1;
		try {
			r = Integer.parseInt(rank);
		} catch (NumberFormatException e){
			errors.add("Rank not a valid integer.");
		}
		return r >= 0 && r <= ItemBean.maxRank();
	}

	private boolean isStatusValid(List<String> errors) {
		if(status == null || status.isEmpty())
			return true; //probably using a form that doesn't have status
		
		if(status.length() >= 255)
			errors.add("Status is over 255 characters long.");
		
		int s = -1;
		try {
			s = Integer.parseInt(status);
		} catch (NumberFormatException e){
			errors.add("Status not a valid integer.");
		}
		return s >= 0 && s <= ItemBean.maxStatus();
	}
	
	private boolean isURLValid(List<String> errors) {

		if(url.length() >= 255)
			errors.add("URL is over 255 characters long.");
		
		return url.startsWith("http://");
	}
	
	private boolean isPointsValid(List<String> errors) {
		if(points == null || points.isEmpty())
			return true; //probably using a form that doesn't have points

		if(points.length() >= 255)
			errors.add("Points is over 255 characters long.");
		
		int p = -1;
		try {
			p = Integer.parseInt(points);
		} catch (NumberFormatException e){
			errors.add("Points not a valid integer.");
		}
		return p >= 0;
	}
	
	private boolean isPriceValid(List<String> errors) {

		if(price.length() >= 255)
			errors.add("Price is over 255 characters long.");
		
		double p = -1;
		try {
			p = Double.parseDouble(price);
		} catch (NumberFormatException e){
			return false;
		}
		if (p < 0) return false;
		
		p *= 100;
		p = Math.round(p);
		p /= 100;
		price = p+"";
		return true;
	}
	
	private boolean isFileSizesValid(List<String> errors) {
		boolean isValid = true;
		if (imgFile != null) {
			isValid = isValid && imgFile.getBytes().length < FILE_MAX_LENGTH;
		}
		if (actionFile != null) {
			isValid = isValid && imgFile.getBytes().length < FILE_MAX_LENGTH;
		}
		return isValid;
	}
}
