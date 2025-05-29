package pom;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.LogHelper;
import utils.RandomGenerator;

import java.util.List;

public class EditorPage extends BaseTest  implements BasePage
{
	private Page page;
	private Locator publishArticleButton;
	private Locator articleTitleInput;
	private Locator articleAboutInput;
	private Locator articleContentInput;
	private Locator articleTagsInput;
	public EditorPage(Page page){
		if (page == null) {
			LogHelper.error("Page object cannot be null");
			throw new IllegalArgumentException("Page object cannot be null");
		}
		this.page = page;
		this.publishArticleButton = page.locator("//button[text()='Publish Article']");
		this.articleTitleInput = page.locator("//input[@name='title']");
		this.articleAboutInput = page.locator("//input[@name='description']");
		this.articleContentInput = page.locator("//textarea[@name='body']");
		this.articleTagsInput = page.locator("//input[@name='tags']");
	}
	@Override
	public boolean isAtCorrectPage()
	{
		waitForNetworkIdle(page);
		boolean isVisible = waitForVisibility(publishArticleButton);
		if (isVisible) {
			LogHelper.debug("Publish article button is visible. User is at the correct page");
		} else {
			LogHelper.warn("Publish article button is not visible. User might not be at the correct page");
		}
		return isVisible;
	}

	public void publishArticle(String articleTitle, String articleAbout, String articleContent, List<String> articleTags){
		String randomString = RandomGenerator.getRandomString("Random-");
		fillField(articleTitleInput, articleTitle+randomString);
		fillField(articleAboutInput, articleAbout+randomString);
		fillField(articleContentInput, articleContent+randomString);
		for (String tag : articleTags)
		{
			fillField(articleTagsInput, tag);
		}

		clickOnElement(publishArticleButton);
	}

}
