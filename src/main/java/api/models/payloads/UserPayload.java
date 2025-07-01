package api.models.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents details of a user.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public class UserPayload
{

	final private String email;
	final private String password;

}