package base;

import constants.LocatorType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PredefinedActions {

    private static WebDriver driver;

    protected void launchBrowser(String browser, String url){
        switch(browser.toUpperCase()){
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);
//                System.setProperty("webdriver.chrome.driver","/Users/varsha.tiwari/PycharmProjects/RobotFrameworkPractice/chromedriver_mac64/chromedriver");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "IE" :
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            case "SAFARI":
                WebDriverManager.iedriver().setup();
                driver = new SafariDriver();
                driver.manage().window().maximize();
            default:
                throw new IllegalStateException();
        }
        driver.manage().window().maximize();
        driver.get(url);
    }

    protected WebElement getElementWithoutWait(String locatorType, String locatorValue){
        WebElement element = null;
        switch(locatorType) {
            case LocatorType.XPATH:
                element  = driver.findElement(By.xpath(locatorValue));
                break;
            case LocatorType.ID:
                element  = driver.findElement(By.id(locatorValue));
                break;
            case LocatorType.CLASS_NAME:
                element  = driver.findElement(By.className(locatorValue));
                break;
            case LocatorType.TAG_NAME:
                element  = driver.findElement(By.tagName(locatorValue));
                break;
            case LocatorType.LINK_TEXT:
                element  = driver.findElement(By.linkText(locatorValue));
                break;
            case LocatorType.PARTIAL_LINK_TEXT:
                element  = driver.findElement(By.partialLinkText(locatorValue));
                break;
            case LocatorType.CSS_SELECTOR:
                element  = driver.findElement(By.cssSelector(locatorValue));
                break;
        }
        return element;
    }


    private WebElement getElementWithWait(String locatorType, String locatorValue){
        WebElement element = null;
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        switch(locatorType.toLowerCase(Locale.ROOT)) {
            case LocatorType.XPATH:
                element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
                break;
            case LocatorType.ID:
                element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                break;
            case "classname":
                element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
                break;
            case LocatorType.TAG_NAME:
                element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
                break;
            case LocatorType.LINK_TEXT:
                element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
                break;
            case LocatorType.PARTIAL_LINK_TEXT:
                element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
                break;
            case LocatorType.CSS_SELECTOR:
                element  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
                break;
            default:
                System.out.println("locator not found");
        }
        return element;
    }

    private ArrayList<String> getLocatorDetails(String locator){
        String elementData[] = locator.split("]:-");
        String locatorType = elementData[0].substring(1);
        String locatorValue = elementData[1];
        ArrayList<String> locatorData = new ArrayList<String>();
        locatorData.add(locatorType);
        locatorData.add(locatorValue);
        return locatorData;
    }

    private WebElement getElement(String locator, boolean isWaitRequired){
        WebElement element = null;
        ArrayList<String> locatorDetails = getLocatorDetails(locator);
        if(isWaitRequired)
            element = getElementWithWait(locatorDetails.get(0), locatorDetails.get(1));
        else
            element = getElementWithoutWait(locatorDetails.get(0), locatorDetails.get(1));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
        return element;
    }

    protected String getElementText(String locator, boolean isWaitRequired){
        return getElement(locator,isWaitRequired).getText();
    }

    protected void clickOnElement(String locator, boolean isWaitRequired){
        getElement(locator,isWaitRequired).click();
    }

    protected boolean checkIfElementIsVisible(String locator, boolean isWaitRequired){
        return getElement(locator,isWaitRequired).isDisplayed();
    }

    protected boolean checkIfElementIsSelected(String locator, boolean isWaitRequired){
        return getElement(locator,isWaitRequired).isSelected();
    }

    protected boolean checkIfElementIsEnabled(String locator, boolean isWaitRequired){
        return getElement(locator,isWaitRequired).isEnabled();
    }

    protected void clickOnSubmitButton(String locator, boolean isWaitRequired){
        getElement(locator,isWaitRequired).submit();
    }

    protected void clearInputFieldText(String locator, boolean isWaitRequired){
        getElement(locator,isWaitRequired).clear();
    }

    protected void enterText(String locator, String text, boolean isWaitRequired){
        getElement(locator,isWaitRequired).sendKeys(text);
    }

    protected void selectElementByVisibleText(String locator, String txt, boolean isWaitRequired){
        Select select = new Select(getElement(locator, isWaitRequired));
        select.selectByVisibleText(txt);
    }

    protected void selectElementByIndex(String locator, int index, boolean isWaitRequired){
        Select select = new Select(getElement(locator, isWaitRequired));
        select.selectByIndex(index);
    }

    protected void selectByValue(String locator, String value, boolean isWaitRequired){
        Select select = new Select(getElement(locator, isWaitRequired));
        select.selectByValue(value);
    }

    public List<WebElement> getDropDownOptions(String locator, boolean isWaitRequired){
        Select select = new Select(getElement(locator, isWaitRequired));
        return select.getOptions();
    }

    public boolean checkIfDropdownIsMultiSelect(String locator, boolean isWaitRequired){
        Select select = new Select(getElement(locator, isWaitRequired));
        return select.isMultiple();
    }

    public void acceptAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public List<WebElement> getListOfElements(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ArrayList<String> locatorDetails = getLocatorDetails(locator);
        List<WebElement> listElement=null;
        switch (locatorDetails.get(0)) {
            case LocatorType.XPATH:
                listElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorDetails.get(1))));
                break;
            case LocatorType.ID:
                listElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorDetails.get(1))));
                break;
            case LocatorType.CLASS_NAME:
                listElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locatorDetails.get(1))));
                break;
            case LocatorType.TAG_NAME:
                listElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locatorDetails.get(1))));
                break;
            case LocatorType.LINK_TEXT:
                listElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorDetails.get(1))));
                break;
            case LocatorType.PARTIAL_LINK_TEXT:
                listElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locatorDetails.get(1))));
                break;
            case LocatorType.CSS_SELECTOR:
                listElement = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorDetails.get(1))));
                break;
        }
        return listElement;
    }

    public String getElementAttributeValue(String locator, boolean isWaitRequired){
        return getElement(locator, isWaitRequired).getAttribute("value");
    }

    public void takeSnapShot(){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./src/test/screenshots/screenshot"+getCurrentTimeStamp()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentTimeStamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
        Calendar calendar  = Calendar.getInstance();
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        return formattedDate;
    }

    public void closeBrowser(){
        driver.quit();
    }

    public String replaceTextInLocator(String locator, String text){
        locator = locator.replace("%s", text);
        System.out.println(locator);
        return locator;

    }
}
