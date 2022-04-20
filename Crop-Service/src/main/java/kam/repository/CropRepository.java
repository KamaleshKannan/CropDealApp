package kam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kam.model.Crop;



public interface CropRepository extends MongoRepository<Crop, String> {

}