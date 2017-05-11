package service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persistence.model.ProductModel;
import persistence.model.RoleModel;
import persistence.model.TransactionModel;
import persistence.model.UserModel;
import persistence.model.UserTokenModel;
import persistence.repository.RoleRepository;
import persistence.repository.UserRepository;
import persistence.repository.UserTokenRepository;
import service.helper.TokenGenerator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository userRoleRepository;

	@Autowired
	private UserTokenRepository userTokenRepository;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TokenGenerator userTokenGenerator;

	@Override
	public void createUser(UserModel user) {
		String token = userTokenGenerator.generateToken();

		RoleModel role = userRoleRepository.findByRole("ROLE_USER");
		user.setRole(role);

		UserTokenModel userToken = new UserTokenModel();
		userToken.setToken(token);
		userToken.setDate(new Date());
		userTokenRepository.save(userToken);

		userToken = userTokenRepository.findByToken(token);
		user.setUserToken(userToken);
		userRepository.save(user);
	}

	@Override
	public boolean verify(String userToken) {
		UserTokenModel token = userTokenRepository.findByToken(userToken);
		boolean hasExpired = false;
		Date currentDate = new Date();
		long timeElapsed = currentDate.getTime() - token.getDate().getTime();
		if (timeElapsed > 5) {
			hasExpired = true;
		}
		return hasExpired;
	}

	@Override
	public int getUserId() {
		String username = getUsername();
		return userRepository.findByUsername(username).getId();
	}

	@Override
	public UserModel findById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public Set<ProductModel> getOwnedCourses() {
		Set<ProductModel> products = new HashSet<>();
		List<TransactionModel> transactions = transactionService.getPurchesedTransactions();
		for (TransactionModel transaction : transactions) {
			addProducts(products, transaction);
		}
		return products;
	}

	private void addProducts(Set<ProductModel> products, TransactionModel transaction) {
		for (ProductModel product : transaction.getProducts()) {
			products.add(product);
		}
	}

	@Override
	public String getUsername() {
		return "user";
	}

}
