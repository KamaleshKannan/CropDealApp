package com.systemcropdetails.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.systemcropdetails.model.CropDetails;
import com.systemcropdetails.repo.CropDetailsRepo;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CropsDetailsController {
	

	
	@Autowired
   CropDetailsRepo repo;
	
	@RequestMapping(value="/crop",method=RequestMethod.GET)
	public List<CropDetails> getAllCropsDetails(){
		return repo.findAll();
	}
	
	@RequestMapping(value="/crop/{id}",method=RequestMethod.GET)
	  public List<CropDetails> getCropsDetails(@PathVariable String id) {
		return repo.findByFarmerId(id);
	  }
	
	@RequestMapping(value="/farmerCrop/{farmerId}",method=RequestMethod.GET)
	  public List<CropDetails> getByFarmerId(@PathVariable String farmerId) {
		return repo.findByFarmerId(farmerId);
	  }
	

	  @RequestMapping(value="/addcrop",method=RequestMethod.POST)
	  public List<CropDetails> addCropDetails(@RequestBody CropDetails crops) {
		  return (List<CropDetails>) repo.insert(crops);
		  
	  }

	  @RequestMapping(value="/crop/{id}",method=RequestMethod.PUT)
	  public List<CropDetails> updateCropsDetails(@PathVariable String id, @RequestBody CropDetails crops) {
		 return (List<CropDetails>) repo.save(crops);
	  }

	  @RequestMapping(value="/crop/{id}",method=RequestMethod.DELETE)
	  public void deleteCropsDetails(@PathVariable String id) {
	    repo.deleteById(id);
	  }
}
