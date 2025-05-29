package testData.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.List;

public class JsonDataProvider {

	@DataProvider(name = "jsonDataProvider")
	public Object[][] provideData() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<TestUserData> testData = mapper.readValue(
				new File("src/main/java/testData/jsonFiles/newArticles.json"),
				mapper.getTypeFactory().constructCollectionType(List.class, TestUserData.class)
		);

		Object[][] dataArray = new Object[testData.size()][4];
		for (int i = 0; i < testData.size(); i++) {
			dataArray[i][0] = testData.get(i).getArticleTitle();
			dataArray[i][1] = testData.get(i).getDescription();
			dataArray[i][2] = testData.get(i).getContent();
			dataArray[i][3] = testData.get(i).getTags();
		}
		return dataArray;
	}
}

class TestUserData {
	private String articleTitle;
	private String description;
	private String content;
	private List<String> tags;

	// Getters y Setters
	public String getArticleTitle() { return articleTitle; }
	public void setArticleTitle(String articleTitle) { this.articleTitle = articleTitle; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

	public List<String> getTags() { return tags; }
	public void setTags(List<String> tags) { this.tags = tags; }
}