package kam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kam.model.Dealer;



public interface DealerRepository extends MongoRepository<Dealer, String> {

}