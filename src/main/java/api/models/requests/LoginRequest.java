package api.models.requests;

import api.models.payloads.UserPayload;
import lombok.Builder;
import lombok.Data;

/**
 * Represents the request payload for user login.
 */
@Data
@Builder
public class LoginRequest
{

	private final UserPayload user;

}
