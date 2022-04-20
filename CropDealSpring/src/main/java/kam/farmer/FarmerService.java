package kam.farmer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FarmerService {
	
	
	public List<Farmer> farmers = new ArrayList<>(Arrays.asList(
			new Farmer("Farmer1","01","12345"),
			new Farmer("Farmer2","02","67891"),
			new Farmer("Farmer3","03","23456")
			));
	
	public List<Farmer> getAllFarmer(){
		return farmers;
	}
	
	public Farmer getFarmer(String id) {
		
		return farmers.stream().filter(t->t.getId().equals(id)).findFirst().get(); 
	}
	
	public void addFarmer(Farmer farmer) {
		farmers.add(farmer);
	}

}
