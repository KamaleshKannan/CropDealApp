package com.systemadmin.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.systemadmin.model.CropDetails;
import com.systemadmin.model.DealersDetails;
import com.systemadmin.model.FarmerDetails;

@Repository
public interface AdminRepository extends MongoRepository<FarmerDetails, String> {

	Optional<FarmerDetails> findById(String farmerId);//handles null pointer exception. 

	List<CropDetails> save(CropDetails crops);

	List <CropDetails>insert(CropDetails crops);

	List<DealersDetails> insert(DealersDetails dealers);

	Optional<DealersDetails> findByDealerId(String dealerId);

	void save(DealersDetails dealers);

	

}
