package service;

import java.util.List;

import persistence.model.ProductModel;

public interface ProductService {

	void addProduct(ProductModel product);

	List<ProductModel> findAllProducts();

	List<ProductModel> findProductsByCategory(String category);

	List<ProductModel> findProductsByPrice(int price);

	ProductModel findProductById(int id);

	void removeById(int id);

}
