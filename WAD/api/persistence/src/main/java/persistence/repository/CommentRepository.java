package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import persistence.model.CommentModel;

@Repository
public interface CommentRepository extends CrudRepository<CommentModel, Integer>{

}
