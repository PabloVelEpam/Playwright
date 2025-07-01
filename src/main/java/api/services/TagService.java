package api.services;

import api.Endpoints;
import api.models.responses.ArticlesResponse;
import api.models.responses.TagsResponse;
import api.utils.JsonUtils;
import api.utils.ResponseUtils;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class TagService extends BaseService
{

	/**
	 * Fetch tags from the /api/tags endpoint.
	 *
	 * @return The deserialized GetTagsResponse object containing the tags and status code.
	 */
	public TagsResponse getTags()
	{
		log.info("Fetching tags from endpoint: {}", Endpoints.GET_TAGS);

		// Perform GET request
		Response response = given(getRequestSpecification()).when().get(Endpoints.GET_TAGS);

		// Log details about the response
		log.info("Received response with status code: {}", response.getStatusCode());

		// Deserialize response body into TagsResponse class
		TagsResponse tagsResponse = JsonUtils.deserialize(response, TagsResponse.class);
		tagsResponse.setStatusCode(response.statusCode());
		tagsResponse.setResponseHeaders(ResponseUtils.getHeadersAsMap(response));

		return tagsResponse;
	}



}
