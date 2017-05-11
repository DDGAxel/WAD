package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persistence.model.ProductModel;
import persistence.model.TransactionModel;
import persistence.model.UserModel;
import persistence.repository.TransactionRepository;


@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;

	public TransactionModel create(TransactionModel transaction) {
		return transactionRepository.save(transaction);
	}

	public TransactionModel getById(int cartId) {
		return transactionRepository.findOne(cartId);
	}

	public void update(TransactionModel cart) {
		transactionRepository.save(cart);
	}

	public void delete(int cartId) {
		transactionRepository.delete(cartId);
	}

	public TransactionModel getActiveTransaction() {
		int userId = userService.getUserId();
		UserModel user = userService.findById(userId);
		List<TransactionModel> courses = transactionRepository.findByUserAndApproval(user, Cart.STATUS_UNFINISHED);
		return courses.get(0);
	}

	public List<TransactionModel> getPurchesedTransactions() {
		int userId = userService.getUserId();
		UserModel user = userService.findById(userId);
		return transactionRepository.findByUserAndApproval(user, Cart.STATUS_FINISHED);
	}

	public void addProductToTransaction(int productId){
		TransactionModel transaction = getActiveTransaction();
		if (transaction == null) {
			int userId = userService.getUserId();
			UserModel user = userService.findById(userId);
			transaction = create(new TransactionModel(user));
		}
		ProductModel product = productService.findProductById(productId);
		if (product == null) {
			throw new IllegalArgumentException("product is null");
		}
		transaction.addTransactionItem(product);
		update(transaction);
	}

	@Override
	public void removeProductFromTransaction(int productId) {
		TransactionModel transaction = getActiveTransaction();
		ProductModel product = productService.findProductById(productId);
		if (product == null) {
			throw new IllegalArgumentException("product is null");
		}
		transaction.removeTransactionItem(product);
		update(transaction);
		
	}

	@Override
	public void clearAllProducts() {
		TransactionModel transaction = getActiveTransaction();
		transaction.getProducts().clear();
		transaction.setGrandTotal(0);
		update(transaction);
		
	}

	@Override
	public void checkout() {
		TransactionModel transaction = getActiveTransaction();
		transaction.setApproval("FINISHED");
		update(transaction);
		int userId = userService.getUserId();
		create(new TransactionModel(userService.findById(userId)));
	}
	
}
