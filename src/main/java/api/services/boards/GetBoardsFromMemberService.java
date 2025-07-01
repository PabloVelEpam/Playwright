package api.services.boards;

import api.ConfigManager;
import api.models.responses.GetBoardFromMemberResponse;
import api.services.BaseService;
import api.utils.JsonUtils;
import api.utils.ResponseUtils;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


@Slf4j
public class GetBoardsFromMemberService extends BaseService
{
	public static final String endpoint = ConfigManager.getBoardsFromMemberEndpoint();

	public List<GetBoardFromMemberResponse> getBoardsFromMember() {
		String endpointWithUsername = String.format(endpoint, ConfigManager.getUsername());
		Response response = given(getRequestSpecification()).when().get(endpointWithUsername);

		List<GetBoardFromMemberResponse> getBoardsFromMemberResponses = JsonUtils.deserializeToList(response,
				GetBoardFromMemberResponse.class);
		getBoardsFromMemberResponses.stream().map(boardResponse -> {
			boardResponse.setStatusCode(response.getStatusCode());
			boardResponse.setResponseHeaders(ResponseUtils.getHeadersAsMap(response));
			return boardResponse;
		}).collect(Collectors.toList());

		return getBoardsFromMemberResponses;
	}

	public GetBoardFromMemberResponse getBoardsFromMember(String username) {
		String endpointWithUsername = String.format(endpoint, username);
		Response response = given(getRequestSpecification()).when().get(endpointWithUsername);

		GetBoardFromMemberResponse getBoardFromMemberResponse = JsonUtils.deserialize(response,
				GetBoardFromMemberResponse.class);
		getBoardFromMemberResponse.setStatusCode(response.statusCode());
		getBoardFromMemberResponse.setResponseHeaders(ResponseUtils.getHeadersAsMap(response));

		return getBoardFromMemberResponse;
	}
}
