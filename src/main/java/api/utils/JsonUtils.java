package api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.List;
import java.util.stream.Collectors;


public class JsonUtils {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T deserialize(Response response, Class<T> clazz) {
		try {
			if(response.getHeader("Content-Encoding").equalsIgnoreCase("zstd")){
				String body = ResponseUtils.getDecompressedBody(response);
				return objectMapper.readValue(body, clazz);
			}
			return objectMapper.readValue(response.body().asString(), clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to parse response", e);
		}
	}

	public static String serialize(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse the string", e);
		}
	}

	public static <T> List<T> deserializeToList(Response response, Class<T> clazz) {
		try {
			List<?> rawList = objectMapper.readValue(response.body().asString(), new TypeReference<>() {
			});

			return rawList.stream().map(item -> objectMapper.convertValue(item, clazz)).collect(Collectors.toList());

		} catch (Exception e) {
			throw new RuntimeException("Failed to parse response to list", e);
		}
	}
}
