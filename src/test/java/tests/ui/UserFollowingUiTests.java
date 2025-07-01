package tests.ui;

import ui.pom.commonActions.LoginActions;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pom.UserProfilePage;
import ui.pom.WelcomePage;
import ui.pom.utils.Hooks;

import java.io.IOException;

public class UserFollowingUiTests extends Hooks
{
	@Test(groups = { "regression" })
	public void followUserUiTest () throws IOException
	{
		LoginActions loginActions = new LoginActions(page);
		loginActions.login();

		WelcomePage welcomePage = new WelcomePage(page);
		welcomePage.selectTagByNumber(1);

		String firstAuthorName = welcomePage.getFirstAuthorName();
		welcomePage.selectFirstAuthor();

		UserProfilePage userProfilePage = new UserProfilePage(page);
		Assert.assertTrue(userProfilePage.isAtCorrectPage());
		Assert.assertTrue(userProfilePage.isCorrectUserName(firstAuthorName));
	}

}
