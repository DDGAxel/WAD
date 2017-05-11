package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import persistence.model.RoleModel;

@Repository
public interface RoleRepository extends CrudRepository<RoleModel, Integer>{

	RoleModel findByRole(String string);

}
