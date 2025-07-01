package tests.api;

import api.models.responses.LoginResponse;
import api.services.BaseService;
import api.services.LoginService;
import testData.dataproviders.LoginCsvDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class SignInApiTests extends BaseService
{

	@Test(groups = { "smoke", "regression" },dataProvider = "csvLoginUserApiDataProvider", dataProviderClass = LoginCsvDataProvider.class)
	public void SignInApiTest(String username, String password, String expectedResult)
	{
		LoginService loginService = new LoginService();
		LoginResponse loginResponse = loginService.customLogin(username, password);

		Assert.assertEquals(loginResponse.getStatusCode(), Integer.parseInt(expectedResult));
	}

}
