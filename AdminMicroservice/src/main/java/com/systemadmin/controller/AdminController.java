package com.systemadmin.controller;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.systemadmin.model.FarmerDetails;
import com.systemadmin.repo.AdminRepository;
import com.systemadmin.model.CropDetails;
import com.systemadmin.model.DealersDetails;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AdminController {
	
	@Autowired
	AdminRepository repo;
	
	 @RequestMapping(value="/farmer",method=RequestMethod.GET)
	  public Optional<FarmerDetails> getAdminDetails(@PathVariable String id) {
		return repo.findById(id);
	  }
	 
	 @RequestMapping(value="/addfarmer",method=RequestMethod.POST)
	  public ResponseEntity<FarmerDetails> addFarmerDetails(@RequestBody FarmerDetails crops) {
		  repo.insert(crops);
		  try {
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	  }
	
	 //@RequestMapping(value="/putfarmer",method=RequestMethod.PUT)
	 // public void addAdminDetails(@RequestBody FarmerDetails farmers) {
	//	  repo.insert(farmers);
	 // }

	 @RequestMapping(value="/farmer",method=RequestMethod.DELETE)
	  public void deleteCropDetails(@PathVariable String userName) {
	    repo.deleteById(userName);
	  }
	 
	 

	  @RequestMapping(value="/addcrop",method=RequestMethod.POST)
	  public void addCropDetails(@RequestBody CropDetails crops) {
		  repo.insert(crops);
	  }

	  @RequestMapping(value="/crop/{id}",method=RequestMethod.PUT)
	  public void updateCropsDetails(@PathVariable String id, @RequestBody CropDetails crops) {
		  repo.save(crops);
	  }
	  

	  @RequestMapping(value="/crop/{id}",method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteCropsDetails(@PathVariable String id) {
	    repo.deleteById(id);
	    try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	  }
	  @RequestMapping(value="/dealers/{DealerId}",method=RequestMethod.GET)
	  public Optional<DealersDetails> getDealersDetails(@PathVariable String DealerId) {
		return repo.findByDealerId(DealerId);
	  }

	  @RequestMapping(value="/adddealers",method=RequestMethod.POST)
	  public void addDealersDetails(@RequestBody DealersDetails dealers) {
		  repo.insert(dealers);
	  }

	  @RequestMapping(value="/dealers/{DealerId}",method=RequestMethod.PUT)
	  public void updateDealersDetails(@PathVariable String DealerId, @RequestBody DealersDetails dealers) {
		  repo.save(dealers);
	  }

	  @RequestMapping(value="/dealers/{DealerId}",method=RequestMethod.DELETE)
	  public void deleteDealersDetails(@PathVariable String DealerId) {
	    repo.deleteById(DealerId);
	  }
}