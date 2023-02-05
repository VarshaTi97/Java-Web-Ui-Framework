package pages;

import base.PredefinedActions;
import constants.FilePaths;
import utils.PropertyFileOperations;

import java.io.File;

public class HomePage extends PredefinedActions {
    private static HomePage homePage = null;
    private PropertyFileOperations propertyFileOperations = null;

    private HomePage(){
        propertyFileOperations = new PropertyFileOperations(FilePaths.HOME_PAGE_LOCATORS);
    }

    public static HomePage getHomePage(){
        if(homePage == null)
            return new HomePage();
        return homePage;
    }

    public boolean isProductsLabelPresent(){
        return checkIfElementIsVisible(propertyFileOperations.getValue("productsLabel"), true);
    }
}
