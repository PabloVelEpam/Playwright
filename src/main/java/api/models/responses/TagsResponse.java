package api.models.responses;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Model representing a response of tags.
 * Extends functionality from BaseService.
 */
@Data
public class TagsResponse
{
	public int statusCode;
	public Map<String, String> responseHeaders;
	private List<String> tags;
}