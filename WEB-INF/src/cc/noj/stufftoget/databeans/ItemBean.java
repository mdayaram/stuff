package cc.noj.stufftoget.databeans;

import java.sql.Date;

public class ItemBean {
	
	public static final int RANK_HIGH = 0,
							RANK_MEDIUM = 1,
							RANK_LOW = 2;
	
	public static final int maxRank() { return RANK_LOW; };
	
	public static final int STATUS_AVAILABLE = 0,
							STATUS_PENDING = 1,
							STATUS_PURCHASED = 2;
	
	public static final int maxStatus() { return STATUS_PURCHASED; }

	private static final int BOGUS_ID = -1;
	
	private final int id;
	private String name;
	private double price;
	private int points;
	private String url;
	private Date datePurchased;
	private Date dateAdded;
	private int imageId;
	private int actionImageId;
	private int purchasedById;
	private int addedById;
	private int rank;
	private int status;
	private String comment;
	private String tags;
	
	public static final String rankToString(int rank){
		switch(rank){
		case RANK_HIGH: return "I need this NOW.";
		case RANK_MEDIUM: return "Good stuff.";
		case RANK_LOW: return "Ok...";
		default: return "Errors!";
		}
	}
	
	public static final String statusToString(int status){
		switch(status){
		case STATUS_AVAILABLE: return "Available";
		case STATUS_PENDING: return "Purchased";
		case STATUS_PURCHASED: return "Received";
		default: return "Errors!";
		}
	}
	
	public ItemBean(int id){
		this.id = id;
	}
	
	public ItemBean(){
		this.id = BOGUS_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int image) {
		this.imageId = image;
	}

	public int getActionImageId() {
		return actionImageId;
	}

	public void setActionImageId(int actionImage) {
		this.actionImageId = actionImage;
	}

	public int getPurchasedById() {
		return purchasedById;
	}

	public void setPurchasedById(int purchasedByID) {
		this.purchasedById = purchasedByID;
	}

	public int getAddedById() {
		return addedById;
	}

	public void setAddedById(int addedByID) {
		this.addedById = addedByID;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getId() {
		return id;
	}
	
}
