package kam.repository;

import org.springframework.context.annotation.Bean;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import kam.models.UserModel;





public interface UserRepository extends MongoRepository<UserModel, String> {
	UserModel findByUsername(String username);
	
}