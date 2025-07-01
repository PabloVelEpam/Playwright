package api.models.requests;

import api.models.payloads.ArticlePayload;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PublishArticleRequest
{
	private final ArticlePayload article;
}
