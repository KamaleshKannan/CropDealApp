package com.systemcropdetails.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.systemcropdetails.model.CropDetails;


public interface CropDetailsRepo extends MongoRepository<CropDetails , String> {
	
	List<CropDetails> findByFarmerId(String farmerId);


}