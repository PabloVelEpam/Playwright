package api.models.requests;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the request payload to create a user.
 */
@Data
@Builder
public class CreateUserRequest
{

	private User user;

	@Data
	@Builder
	public static class User
	{

		private String username;
		private String email;
		private String password;

	}

}
