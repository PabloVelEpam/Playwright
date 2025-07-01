package api.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GetBoardFromMemberResponse {
	public int statusCode;
	public Map<String, String> responseHeaders;
	private String id;
	private String name;
	private String url;
	private String idOrganization;
	private String permissionLevel;
	private String dateLastView;
	private String idMemberCreator;
}