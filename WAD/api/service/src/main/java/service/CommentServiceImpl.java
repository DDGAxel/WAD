package service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import persistence.model.CommentModel;
import persistence.model.ProductModel;
import persistence.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ProductService productService;
	
	public void create(CommentModel comment, int productId) {
		ProductModel product = productService.findProductById(productId);
		comment.setProduct(product);
		
		commentRepository.save(comment);
	}

}
