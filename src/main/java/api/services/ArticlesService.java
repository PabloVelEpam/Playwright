package api.services;

import api.Endpoints;
import api.TestDataFactory;
import api.models.requests.PublishArticleRequest;
import api.models.responses.ArticlesResponse;
import api.models.responses.PublishArticleResponse;
import api.utils.JsonUtils;
import api.utils.ResponseUtils;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class ArticlesService extends BaseService
{

	public ArticlesResponse searchByTag(String tagToSearch)
	{
		log.info("Fetching articles from endpoint: {}", Endpoints.ARTICLES);

		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("tag", tagToSearch);
		queryParams.put("limit", "3");
		queryParams.put("offset", "0");

		// Perform GET request
		Response response = given(getRequestSpecification()).header("authorization", getLoginToken())
															.queryParams(queryParams)
															.when()
															.get(Endpoints.ARTICLES);

		ArticlesResponse articlesResponse = JsonUtils.deserialize(response, ArticlesResponse.class);
		articlesResponse.setStatusCode(response.statusCode());
		articlesResponse.setResponseHeaders(ResponseUtils.getHeadersAsMap(response));

		return articlesResponse;
	}

	public PublishArticleResponse publishArticle(String title, String description, String body, List<String> tagList)
	{
		log.info("Fetching articles from endpoint: {}", Endpoints.ARTICLES);

		PublishArticleRequest publishArticleRequest = TestDataFactory.createArticle(title, description, body, tagList);

		Response response = given(getRequestSpecification()).header("Authorization", getLoginToken())
															.body(JsonUtils.serialize(publishArticleRequest))
															.when()
															.post(Endpoints.ARTICLES);

		log.info(response.prettyPrint());

		PublishArticleResponse articlesResponse = JsonUtils.deserialize(response, PublishArticleResponse.class);
		articlesResponse.setStatusCode(response.statusCode());
		articlesResponse.setResponseHeaders(ResponseUtils.getHeadersAsMap(response));

		return articlesResponse;
	}

}
