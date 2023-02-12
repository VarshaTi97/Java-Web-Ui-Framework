package pages;

import base.PredefinedActions;
import constants.FilePaths;
import utils.PropertyFileOperations;

import java.util.Properties;

public class LaunchPage extends PredefinedActions {

    private static LaunchPage launchPage=null;
    private PropertyFileOperations propertyFileOperations;
    private LaunchPage() {
        propertyFileOperations = new PropertyFileOperations(FilePaths.LAUNCH_PAGE_LOCATORS);
    }

    public static LaunchPage getLaunchPage(){
        if(launchPage == null)
            return new LaunchPage();
        return launchPage;
    }

    public void enterLoginDetails(String username, String password){
        enterText(propertyFileOperations.getValue("usernameField"), username, true);
        enterText(propertyFileOperations.getValue("passwordField"), password, true);
    }

    public HomePage clickOnLoginButton(){
        clickOnElement(propertyFileOperations.getValue("loginButton"),true);
        return HomePage.getHomePage();
    }

    public boolean isLaunchPageLogoPresent(){
        return checkIfElementIsVisible(propertyFileOperations.getValue("loginLogo"),true);
    }

    public boolean isErrorLockedOutMessagePresent(){
        String locator = replaceTextInLocator(propertyFileOperations.getValue("errorMessageLocator"),
                "Epic sadface: Sorry, this user has been locked out.");
        return checkIfElementIsVisible(locator, true);
    }

    public boolean isErrorProblemUserPresent(){
        String locator = replaceTextInLocator(propertyFileOperations.getValue("errorMessageLocator"),
                "Epic sadface: Username and password do not match any user in this service");
        return checkIfElementIsVisible(locator, true);
    }

    public boolean isErrorEmptyUsernameMessagePresent(){
        String locator = replaceTextInLocator(propertyFileOperations.getValue("errorMessageLocator"),
                "Epic sadface: Username is required");
        return checkIfElementIsVisible(locator, true);
    }

    public boolean isErrorEmptyPasswordMessagePresent(){
        String locator = replaceTextInLocator(propertyFileOperations.getValue("errorMessageLocator"),
                "Epic sadface: Password is required");
        return checkIfElementIsVisible(propertyFileOperations.getValue("errorMessageLocator"), true);
    }
}
