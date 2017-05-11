package service;

import java.util.List;

import persistence.model.TransactionModel;


public interface TransactionService {

	TransactionModel create(TransactionModel transaction);

	TransactionModel getById(int cartId);

	void update(TransactionModel cart);

	void delete(int cartId);

	TransactionModel getActiveTransaction();

	List<TransactionModel> getPurchesedTransactions();

	void addProductToTransaction(int productId);
	
	void removeProductFromTransaction(int productId);
	
	void clearAllProducts();

	void checkout();
}
