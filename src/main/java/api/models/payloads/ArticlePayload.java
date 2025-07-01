package api.models.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents details of a user.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ArticlePayload
{

	private String title;
	private String description;
	private String body;
	private List<String> tagList;

}