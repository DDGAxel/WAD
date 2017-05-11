package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import persistence.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{
	
	UserModel findByUsername(String username);
}
