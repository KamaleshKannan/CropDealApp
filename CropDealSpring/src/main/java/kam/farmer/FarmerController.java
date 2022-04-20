package kam.farmer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarmerController {
	
	@Autowired
	private FarmerService farmerService;
	
	@RequestMapping("/farmer")
	public List<Farmer>getAllName(){
		return farmerService.getAllFarmer();
	}
	
	@RequestMapping("/farmer/{id}")
	public Farmer getFarmer(@PathVariable String id) {
		return farmerService.getFarmer(id);

	}
	
	@RequestMapping(value = "/farmer", method = RequestMethod.POST)
	public void addFarmer(@RequestBody Farmer farmer) {
		farmerService.addFarmer(farmer);
	}

}
