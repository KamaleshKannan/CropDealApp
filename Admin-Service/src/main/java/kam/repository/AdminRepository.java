package kam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kam.models.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {

}
