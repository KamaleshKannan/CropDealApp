package kam.farmer;

public class Farmer {
	
	private String Name;
	private String Id;
	private String Phone;
	
	public Farmer(String name, String id, String phone) {
		super();
		this.Name = name;
		this.Id = id;
		this.Phone = phone;
	}
	
	public Farmer() {
		
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getId() {
		return Id;
	}
	public void setFarmerId(String id) {
		Id = id;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}

	
	

}
