import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {
    // Gemaakt door Jens Roets r0764391 & Stijn Hendrix r0797235

    @FindBy(id="userid")
    private WebElement useridField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="signUp")
    private WebElement signUpButton;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "");
    }

    public void gotoPage () {
        this.driver.get(path + "");
    }

    public void loginAsAdmin () {
        gotoPage();
        login("admin", "t");
    }

    public void login (String userid, String password) {
        setUseridField(userid);
        setPasswordField(password);
        signUpButton.click();
    }

    public void setUseridField(String msg) {
        useridField.clear();
        useridField.sendKeys(msg);
    }

    public String getPasswordField() {
        return passwordField.getAttribute("value");
    }

    public String getUseridField() {
        return useridField.getAttribute("value");
    }

    public void setPasswordField(String msg) {
        passwordField.clear();
        passwordField.sendKeys(msg);
    }

    public boolean hasStringOnPage (String str) {
        return driver.getPageSource().contains(str);
    }

    public void submit () {
        signUpButton.click();
    }
}
