package api.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Represents the author of an article.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author
{

	private String username;
	private String bio;
	private String image;
	private boolean following;
	private int followersCount;

}
