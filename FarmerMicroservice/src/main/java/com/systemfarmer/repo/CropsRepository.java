
package com.systemfarmer.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.systemfarmer.model.CropsDetails;

public interface CropsRepository extends MongoRepository<CropsDetails, String>{
	
	List<CropsDetails> findByFarmerId(String farmerId);
   
}
