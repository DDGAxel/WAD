package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import persistence.model.ProductModel;
import service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(params = { "id" })
	public String getProductById(@RequestParam("id") int productId) {
		productService.findProductById(productId);
		return "dummy";
	}

	@GetMapping("/all")
	public String viewCourses() {
		productService.findAllProducts();
		return "dummy";
	}

	@GetMapping("/{category}")
	public String viewCoursesByType(@PathVariable("category") String productCategory) {
		productService.findProductsByCategory(productCategory);
		return "dummy";
	}

	@PostMapping("/add")
	public String processNewCourseForm(@RequestBody ProductModel newProduct) {
		productService.addProduct(newProduct);
		return "dummy";
	}

}
