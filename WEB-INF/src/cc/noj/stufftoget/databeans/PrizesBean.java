package cc.noj.stufftoget.databeans;

public class PrizesBean {

	private static final int BOGUS_ID = -1;
	
	private int id;
	private int points;
	private String action;
	private String name;
	
	public PrizesBean(int id){
		this.id = id;
	}
	
	public PrizesBean() {
		this.id = BOGUS_ID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
