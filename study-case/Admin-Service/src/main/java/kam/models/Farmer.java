package kam.models;

public class Farmer {

	private String id;
	private String farmer_name;
	private String farmer_email;
	private String farmer_contactNo;

	public Farmer(String id, String farmer_name, String farmer_email, String farmer_contactNo) {
		super();
		this.id = id;
		this.farmer_name = farmer_name;
		this.farmer_email = farmer_email;
		this.farmer_contactNo = farmer_contactNo;
	}

//	public Farmer(String id, String farmer_name, String farmer_email, String farmer_contactNo) {
//		super();
//		this.id = id;
//		this.farmer_name = farmer_name;
//		this.farmer_email = farmer_email;
//		this.farmer_contactNo = farmer_contactNo;
//	}

	public String getFarmer_contactNo() {
		return farmer_contactNo;
	}

	public void setFarmer_contactNo(String farmer_contactNo) {
		this.farmer_contactNo = farmer_contactNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Farmer() {
		super();
	}

	public String getFarmer_name() {
		return farmer_name;
	}

	public void setFarmer_name(String farmer_name) {
		this.farmer_name = farmer_name;
	}

	public String getFarmeremail() {
		return farmer_email;
	}

	public void setFarmeremail(String farmer_email) {
		this.farmer_email = farmer_email;
	}

}