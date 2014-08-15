package cc.noj.stufftoget.databeans;

public class RedeemedPrizesBean {

	private static final int BOGUS_ID = -1;
	
	private final int id;
	private int userID;
	private int prizeID;
	
	public RedeemedPrizesBean(int id) {
		this.id = id;
	}
	
	public RedeemedPrizesBean() { 
		this.id = BOGUS_ID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getPrizeID() {
		return prizeID;
	}

	public void setPrizeID(int prizeID) {
		this.prizeID = prizeID;
	}

	public int getId() {
		return id;
	}
}
