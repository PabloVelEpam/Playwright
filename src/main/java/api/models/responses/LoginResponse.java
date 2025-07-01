package api.models.responses;


import api.models.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.Map;

/**
 * Represents the response payload after user login.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoginResponse {
	public int statusCode;
	public Map<String, String> responseHeaders;
	private User user;
}
