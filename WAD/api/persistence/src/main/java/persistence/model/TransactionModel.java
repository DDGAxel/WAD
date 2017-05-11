package persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class TransactionModel extends Model {
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserModel user;

	@ManyToMany//(fetch = FetchType.EAGER)
	@JoinTable(name = "transaction_product", joinColumns = { @JoinColumn(name = "transactionId") }, inverseJoinColumns = {
			@JoinColumn(name = "productId") })
	private List<ProductModel> products = new ArrayList<ProductModel>();

	private int grandTotal;

	private String approval;

	public TransactionModel(){
		
	}
	
	public TransactionModel(UserModel user) {
		this.user = user;
		this.approval = "UNFINISHED";
	}
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	public void addTransactionItem(ProductModel product) {
		if (!products.contains(product)) {
			products.add(product);
		}
		updateGrandTotal();
	}

	public void removeTransactionItem(ProductModel product) {
		products.remove(product);
		updateGrandTotal();
	}

	public void updateGrandTotal() {
		grandTotal = 0;
		for (ProductModel product : products) {
			grandTotal = grandTotal + product.getPrice();
		}
	}
}
