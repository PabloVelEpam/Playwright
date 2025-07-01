package api.models.responses;

import api.models.entities.Article;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Model representing the response for articles. Extends functionality from BaseService.
 */
@Data
public class ArticlesResponse
{

	public int statusCode;
	public Map<String, String> responseHeaders;
	private List<Article> articles;
	private int articlesCount;

}
