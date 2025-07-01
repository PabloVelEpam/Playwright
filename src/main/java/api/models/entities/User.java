package api.models.entities;

import api.services.BaseService;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * Represents details of a user.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Jacksonized
public class User extends BaseService
{

	private String email;
	private String username;
	private String bio;
	private String image;
	private String token;

}