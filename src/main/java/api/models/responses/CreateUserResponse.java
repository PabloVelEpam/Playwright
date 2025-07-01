package api.models.responses;

import lombok.Data;

import java.util.Map;

/**
 * Represents the response payload after creating a user.
 */
@Data
public class CreateUserResponse
{

	private User user; // Contains user details from the response
	private int statusCode; // For storing HTTP status code
	private Map<String, String> responseHeaders; // For storing response headers

	@Data
	public static class User
	{

		private String username;
		private String email;
		private String bio;
		private String image;
		private String token;

	}

}
