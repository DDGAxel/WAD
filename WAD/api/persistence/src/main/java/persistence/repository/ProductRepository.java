package persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import persistence.model.ProductModel;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Integer> {

	@Override
	List<ProductModel> findAll();

	List<ProductModel> findByGenre(String genre);

	List<ProductModel> findByPrice(int price);

	ProductModel findById(int id);
}
