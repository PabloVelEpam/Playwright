package api.utils;

import com.github.luben.zstd.Zstd;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseUtils
{

	public static Map<String, String> getHeadersAsMap(Response response)
	{
		return response.getHeaders().asList().stream().collect(Collectors.toMap(Header::getName, Header::getValue));
	}

	public static String getDecompressedBody(Response response)
	{
		String contentEncoding = response.getHeader("Content-Encoding");
		System.out.println("Content-Encoding: {}"+ contentEncoding);

		try
		{
			if ("zstd".equalsIgnoreCase(contentEncoding))
			{

				byte[] compressedBytes = response.body().asInputStream().readAllBytes();
				System.out.println("Compressed Bytes Length: {}" + compressedBytes.length);

				if (compressedBytes == null || compressedBytes.length == 0)
				{
					throw new RuntimeException("Received invalid compressed body");
				}


				int maxDecompressedSize = 10 * 1024 * 1024;
				byte[] decompressedBytes = new byte[maxDecompressedSize];
				long decompressedLength = Zstd.decompress(decompressedBytes, compressedBytes);

				decompressedBytes = Arrays.copyOf(decompressedBytes, (int) decompressedLength);

				return new String(decompressedBytes, StandardCharsets.UTF_8);
			}
			else
			{

				return response.body().asString();
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException("Failed to decompress response body", e);
		}
	}

}
