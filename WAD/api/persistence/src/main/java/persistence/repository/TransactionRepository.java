package persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import persistence.model.TransactionModel;
import persistence.model.UserModel;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, Integer>{
	
	//@Query("SELECT t FROM Transaction t where t.user = :user and t.approval = :approval")
	//List<TransactionModel> findByUserIdAndApproval(@Param("user") UserModel user, @Param("approval") String approval);
	List<TransactionModel> findByUserAndApproval(UserModel user, String approval);
}
