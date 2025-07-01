package api.models.responses;

import api.models.entities.Article;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Builder
@Jacksonized
public class PublishArticleResponse
{
	public int statusCode;
	public Map<String, String> responseHeaders;
	private final Article article;
}
