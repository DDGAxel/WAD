package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import persistence.model.TransactionModel;
import service.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping
	public TransactionModel transaction(Model model) {
		TransactionModel transaction = transactionService.getActiveTransaction();
		return transaction;
	}

	@PostMapping("add/{productId}")
	public String addItem(@PathVariable("productId") int productId) {
		transactionService.addProductToTransaction(productId);
		return "dummy";
	}

	@DeleteMapping("/remove/{productId}")
	public String removeItem(@PathVariable("productId") int productId) {
		transactionService.removeProductFromTransaction(productId);
		return "dummy";
	}

	@PutMapping("/clear")
	public String clearCart() {
		transactionService.clearAllProducts();
		return "cart";
	}

	@PutMapping("/checkout")
	public String transactionCheckout() {
		transactionService.checkout();
		return "cart";
	}

}
