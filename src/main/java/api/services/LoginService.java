package api.services;

import api.Endpoints;
import api.TestDataFactory;
import api.models.requests.LoginRequest;
import api.models.responses.LoginResponse;
import api.utils.JsonUtils;
import api.utils.ResponseUtils;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class LoginService extends BaseService {

	/**
	 * Sends a login request to the /api/users/login endpoint.
	 *
	 * @return The deserialized LoginResponse containing user details and status code.
	 */
	public LoginResponse regularLogin() {
		log.info("Attempting to login using endpoint: {}", Endpoints.LOGIN);

		LoginRequest loginRequest = TestDataFactory.createDefaultLoginRequest();

		// Perform POST request with the provided payload
		Response response = given(getRequestSpecification())
				.body(loginRequest)
				.when()
				.post(Endpoints.LOGIN);

		log.info("Received response with status code: {}", response.getStatusCode());

		// Deserialize the response body into LoginResponse
		LoginResponse loginResponse = JsonUtils.deserialize(response, LoginResponse.class);
		loginResponse.setStatusCode(response.statusCode());
		loginResponse.setResponseHeaders(ResponseUtils.getHeadersAsMap(response));

		setLoginToken(loginResponse.getUser().getToken());

		return loginResponse;
	}

	public LoginResponse customLogin(String username, String password) {
		log.info("Attempting to login using endpoint: {}", Endpoints.LOGIN);

		LoginRequest loginRequest = TestDataFactory.createCustomUser(username, password);

		// Perform POST request with the provided payload
		Response response = given(getRequestSpecification())
				.body(loginRequest)
				.when()
				.post(Endpoints.LOGIN);

		log.info("Received response with status code: {}", response.getStatusCode());

		// Deserialize the response body into LoginResponse
		LoginResponse loginResponse = new LoginResponse();
		if(response.getStatusCode() == 200){
			loginResponse = JsonUtils.deserialize(response, LoginResponse.class);
			setLoginToken(loginResponse.getUser().getToken());
		}
		loginResponse.setStatusCode(response.statusCode());
		loginResponse.setResponseHeaders(ResponseUtils.getHeadersAsMap(response));

		return loginResponse;
	}
}
