package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persistence.model.ProductModel;
import persistence.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductModel> findAllProducts() {
		List<ProductModel> products = productRepository.findAll();
		return products;
	}

	public void addCourse(ProductModel product) {
		productRepository.save(product);
	}

	@Override
	public List<ProductModel> findProductsByCategory(String category) {
		List<ProductModel> products = productRepository.findByGenre(category);
		return products;
	}

	@Override
	public ProductModel findProductById(int id) {
		return productRepository.findById(id);
	}

	@Override
	public void removeById(int id) {
		productRepository.delete(id);
	}

	@Override
	public void addProduct(ProductModel product) {
		productRepository.save(product);
	}

	@Override
	public List<ProductModel> findProductsByPrice(int price) {
		List<ProductModel> products = productRepository.findByPrice(price);
		return products;
	}

}
