package base;

import constants.FilePaths;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.PropertyFileOperations;


public class ITestListenerImpl extends PredefinedActions implements ITestListener {

    private PropertyFileOperations propertyFileOperations = new PropertyFileOperations(FilePaths.CONFIG_FILE_PATH);

    public void onTestStart(ITestResult result) {
        launchBrowser(propertyFileOperations.getValue("browser"), propertyFileOperations.getValue("url"));
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test has succeeded for testcase:" + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        takeSnapShot();
        closeBrowser();
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test has skipped for testcase:" + result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test has succeeded with percentage for testcase:" + result.getName());
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        System.out.println("Test has started for testcase:" + context.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test has finished for testcase:" + context.getName());
        closeBrowser();
    }
}
