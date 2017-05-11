package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import persistence.model.UserTokenModel;

@Repository
public interface UserTokenRepository extends CrudRepository<UserTokenModel, Integer>{

	UserTokenModel findByToken(String token);

}
