package tests.ui;

import ui.pom.commonActions.LoginActions;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pom.WelcomePage;
import ui.pom.utils.Hooks;

import java.io.IOException;

public class SearchByTagUiTests extends Hooks
{
	@Test(groups = { "regression" })
	public void searchTagByNameUiTest() throws IOException
	{
		LoginActions loginActions = new LoginActions(page);
		loginActions.login();

		WelcomePage welcomePage = new WelcomePage(page);
		String tagToSearch = "implementation";
		welcomePage.selectTagByName(tagToSearch);

		Assert.assertTrue(welcomePage.isTagPresent(tagToSearch));
	}

}
