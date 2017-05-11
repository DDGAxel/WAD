package service;

import java.util.Set;

import persistence.model.ProductModel;
import persistence.model.UserModel;



public interface UserService {

	public void createUser(UserModel user);

	public boolean verify(String userToken);

	public int getUserId();

	public UserModel findById(int id);

	public String getUsername();

	public Set<ProductModel> getOwnedCourses();

}
