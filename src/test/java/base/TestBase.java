package base;

import constants.FilePaths;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import pages.HomePage;
import pages.LaunchPage;
import utils.ExcelFileReader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class TestBase extends ITestListenerImpl{
    LaunchPage launchPage = LaunchPage.getLaunchPage();
    HomePage homePage = HomePage.getHomePage();

//    @DataProvider(name="launchDataFromExcel")
//    public Object[][] getLoginData(Method method){
//        return ExcelFileReader.getData(FilePaths.TEST_DATA_PATH, method.getName());
//    }
//

//    @Parameters(value={"browserName"})
//    @BeforeTest
//    public void getBrowserName(String browserName){
//        System.out.println(browserName);
//    }

    @DataProvider(name="getDataFromExcel")
    public Iterator<Object[]> getLoginTestCaseData(Method method) throws IOException {
        return ExcelFileReader.getMultipleData(FilePaths.TEST_DATA_PATH, method.getName());
    }
}
