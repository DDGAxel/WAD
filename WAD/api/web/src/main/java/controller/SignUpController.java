package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controller.response.SignupResponse;
import persistence.model.UserModel;
import service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	private UserService userService;

	@PostMapping
	public SignupResponse processNewUserFrom(@RequestBody UserModel newUser) {
		userService.createUser(newUser);
		SignupResponse response = new SignupResponse();
		response.setMessage(
				"Thank you for registering. Please confirm your email within 24 or your account will be deleted. You may proceed to login");
		response.setStatus(200);
		return response;
	}

}
