package com.systemfarmer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemfarmer.model.CropsDetails;
import com.systemfarmer.repo.CropsRepository;

@Service
public class FarmerService {
	@Autowired
	public CropsRepository CropDetailsRepo;
	private String cropId;

	public Boolean addCrop(CropsDetails crop) {
		CropDetailsRepo.insert(crop);
		return true;
	}

	public List<CropsDetails> getAllCrop() {

		return CropDetailsRepo.findAll();
	}

	public List<CropsDetails> getAllCropByFarmer(String farmerId) {
		return CropDetailsRepo.findByFarmerId(farmerId);
	}

	public boolean deleteCrop(String cropId) {
		CropDetailsRepo.deleteById(cropId);
		return true;

	}

	public String updateCrop(CropsDetails crop) {

		CropDetailsRepo.save(crop);
		return "Crop UPdated Successfully";
	}

	public List<CropsDetails> findAll() {
		return CropDetailsRepo.findAll();
	}

	public Optional<CropsDetails> findById(String id) {
		CropDetailsRepo.findById(cropId);
		return null;
	}


}
