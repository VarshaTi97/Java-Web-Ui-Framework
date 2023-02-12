package base;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;

//@Listeners(ITestListenerImpl.class)
public class LaunchPageTest extends TestBase {

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = TestBase.class,
            testName = "Verify_LoginData_With_Standard_User")
    public void Verify_LoginData_With_Standard_User(String username, String password){
//        String browser = System.getProperty("browser");
//        String url = System.getProperty("url");
//        System.out.println("---------browser-------");
//        System.out.println(browser);
//        System.out.println("---------url-------");
//        System.out.println(url);
        launchPage.enterLoginDetails(username,password);
        HomePage homePage = launchPage.clickOnLoginButton();
        Assert.assertTrue(homePage.isProductsLabelPresent());
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = TestBase.class,
            testName = "Verify_LoginData_With_Locked_Out_User")
    public void Verify_LoginData_With_Locked_Out_User(String username, String password){
        launchPage.enterLoginDetails(username,password);
        launchPage.clickOnLoginButton();
        Assert.assertTrue(launchPage.isErrorLockedOutMessagePresent());
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = TestBase.class, testName =
            "Verify_LoginData_With_Empty_Username")
    public void Verify_LoginData_With_Empty_Username(String username, String password){
        launchPage.enterLoginDetails(username,password);
        launchPage.clickOnLoginButton();
        Assert.assertTrue(launchPage.isErrorEmptyUsernameMessagePresent());
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = TestBase.class,
            testName = "Verify_LoginData_With_Problem_User")
    public void Verify_LoginData_With_Problem_User(String username, String password){
        launchPage.enterLoginDetails(username,password);
        launchPage.clickOnLoginButton();
        Assert.assertTrue(launchPage.isErrorProblemUserPresent());
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = TestBase.class,
            testName = "Verify_LoginData_With_Empty_Password")
    public void Verify_LoginData_With_Empty_Password(String username, String password){
        launchPage.enterLoginDetails(username,password);
        launchPage.clickOnLoginButton();
        Assert.assertTrue(launchPage.isErrorEmptyPasswordMessagePresent());
    }
}
