package kam.models;

public class Crop {

	private String id;
	private String crop_name;
	private String crop_type;
	private String crop_quantity;

	
	

public Crop(String id, String crop_name, String crop_type, String crop_quantity) {
		super();
		this.id = id;
		this.crop_name = crop_name;
		this.crop_type = crop_type;
		this.crop_quantity = crop_quantity;
	}

//	public Crop(String id, String crop_name, String crop_type, String crop_quantity) {
//		super();
//		this.id = id;
//		this.crop_name = crop_name;
//		this.crop_type = crop_type;
//		this.crop_quantity = crop_quantity;
//	}

	public String getCrop_quantity() {
		return crop_quantity;
	}

	public void setCrop_quantity(String crop_quantity) {
		this.crop_quantity = crop_quantity;
	}

	public Crop() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCrop_name() {
		return crop_name;
	}

	public void setCrop_name(String crop_name) {
		this.crop_name = crop_name;
	}

	public String getCrop_type() {
		return crop_type;
	}

	public void setCrop_type(String crop_type) {
		this.crop_type = crop_type;
	}

}

