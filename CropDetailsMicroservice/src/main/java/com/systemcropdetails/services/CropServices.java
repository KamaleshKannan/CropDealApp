package com.systemcropdetails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemcropdetails.model.CropDetails;
import com.systemcropdetails.repo.CropDetailsRepo;

@Service
public class CropServices {

	@Autowired
	CropDetailsRepo CropDetailsRepo;
	private String cropId;
	
	public Boolean addCrop(CropDetails crop) {
		CropDetailsRepo.insert(crop);
		return true;
		}

	public List<CropDetails> getAllCrop() {
		
		return CropDetailsRepo.findAll();
	}

	public List<CropDetails> getAllCropByFarmer(String farmerId) 
	{
		return CropDetailsRepo.findByFarmerId(farmerId);
	}

	public boolean deleteCrop(String cropId) {
		CropDetailsRepo.deleteById(cropId);
		return true;
		
	}

	public String updateCrop(CropDetails crop) {
		// TODO Auto-generated method stub
		CropDetailsRepo.save(crop);
		return "Crop UPdated Successfully";
	}

	public List<CropDetails> findAll() {
		
		 return CropDetailsRepo.findAll();
	}

	public Optional<CropDetails> findById(String id) {
		CropDetailsRepo.findById(cropId);
		return null;
	}

	/*
	 * public void updateCropByFarmer(String cropId, CropModel crop) {
	 * cropRepository.
	 * 
	 * }
	 */
	
	
	
}
