package service.helper;

import java.util.UUID;

public class UserTokenGenerator implements TokenGenerator {

	public String generateToken() {
		String token;

		UUID uuid = UUID.randomUUID();
		token = uuid.toString();
		return token;
	}

}