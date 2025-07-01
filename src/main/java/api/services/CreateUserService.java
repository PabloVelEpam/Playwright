package api.services;

import api.Endpoints;
import api.models.requests.CreateUserRequest;
import api.models.responses.CreateUserResponse;
import api.utils.JsonUtils;
import api.utils.ResponseUtils;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class CreateUserService extends BaseService {

	/**
	 * Registers a new user in the system.
	 *
	 * @param createUserRequest The body payload that contains user details (username, email, password).
	 * @return The deserialized CreateUserResponse containing the created user's details and status code.
	 */
	public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
		log.info("Registering a new user using endpoint: {}", Endpoints.CREATE_USER);

		// Perform POST request with the provided payload
		Response response = given(getRequestSpecification())
				.body(createUserRequest) // Serialize the CreateUserRequest object to JSON
				.when()
				.post(Endpoints.CREATE_USER);

		// Log details about the response
		log.info("Received response with status code: {}", response.getStatusCode());

		// Deserialize the response body into CreateUserResponse
		CreateUserResponse createUserResponse = JsonUtils.deserialize(response, CreateUserResponse.class);
		createUserResponse.setStatusCode(response.statusCode());
		createUserResponse.setResponseHeaders(ResponseUtils.getHeadersAsMap(response));

		return createUserResponse;
	}
}
