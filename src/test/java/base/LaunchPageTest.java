package base;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

@Listeners(ITestListenerImpl.class)
public class LaunchPageTest extends TestBase {

    @Test(dataProvider = "launchDataFromExcel")
    public void checkLoginWithMultipleUsers(String username, String password){
        launchPage.enterLoginDetails(username,password);
        HomePage homePage = launchPage.clickOnLoginButton();
        Assert.assertTrue(homePage.isProductsLabelPresent());
    }
}
