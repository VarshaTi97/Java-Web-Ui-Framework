package base;

import constants.FilePaths;
import org.testng.annotations.DataProvider;
import pages.HomePage;
import pages.LaunchPage;
import utils.ExcelFileReader;

public class TestBase extends ITestListenerImpl{
    LaunchPage launchPage = LaunchPage.getLaunchPage();
    HomePage homePage = HomePage.getHomePage();

    @DataProvider(name="launchDataFromExcel")
    public Object[][] getLoginData(){
        return ExcelFileReader.getData(FilePaths.TEST_DATA_PATH, "LaunchData");
    }

}
