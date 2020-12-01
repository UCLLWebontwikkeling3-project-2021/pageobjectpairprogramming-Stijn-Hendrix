import domain.model.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.sql.Date;

import static org.junit.Assert.*;

public class LoginTest {
    // Gemaakt door Jens Roets r0764391 & Stijn Hendrix r0797235

    private WebDriver driver;
    private String path = "http://localhost:8080/Contact_Tracing_war_exploded/Controller";

    HomePage homePage;

    static String adminUserid = "admin";
    static String adminPassword = "t";

    String jensUserid = "jens";
    String jensPassword = "roets";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\School\\chromedriver.exe");
        driver = new ChromeDriver();

        homePage = PageFactory.initElements(driver, HomePage.class);

    }

    @Test
    public void test_Login_AllFieldsFilledInCorrectly_UserIsLoggedIn () {

        homePage.setUseridField(adminUserid);
        homePage.setPasswordField(adminPassword);
        homePage.submit();

        String loggedInMessage = "Welcome " + adminUserid + "!";
        assertEquals(homePage.getTitle(), "Home");
        assertTrue(homePage.hasStringOnPage(loggedInMessage));
    }


    @Test
    public void test_Login_UseridNotFilledIn_ErrorMessageGivenForUseridAndOtherFieldsValueKept () {
        homePage.setUseridField(adminUserid);

        System.out.println( homePage.getUseridField());
        homePage.setPasswordField("");
        homePage.submit();

        String failedLoginMsg = "No matching userid/password";

        assertEquals(homePage.getTitle(), "Home");
        assertTrue(homePage.hasStringOnPage(failedLoginMsg));

        assertEquals("", homePage.getPasswordField());
        assertEquals(adminUserid, homePage.getUseridField());
    }


    @Test
    public void test_Login_PasswordNotFilledIn_ErrorMessageGivenForPasswordAndOtherFieldsValueKept () {
        homePage.setUseridField("");
        homePage.setPasswordField(adminPassword);
        homePage.submit();

        String failedLoginMsg = "No matching userid/password";

        assertEquals(homePage.getTitle(), "Home");
        assertTrue(homePage.hasStringOnPage(failedLoginMsg));
        assertEquals(adminPassword, homePage.getPasswordField());
        assertEquals("", homePage.getUseridField());
    }

    @Test
    public void test_Login_AllFieldsFilledIn_ErrorMessageGivenForAllFields () {
        homePage.setUseridField(jensUserid);
        homePage.setPasswordField(jensPassword);
        homePage.submit();

        String failedLoginMsg = "No matching userid/password";

        assertEquals(homePage.getTitle(), "Home");
        assertTrue(homePage.hasStringOnPage(failedLoginMsg));
        assertEquals(jensUserid, homePage.getUseridField());
        assertEquals(jensPassword, homePage.getPasswordField());
    }

    @Test
    public void test_Login_NoFieldsFilledIn_ErrorMessageGivenForAllFields () {
        homePage.setUseridField("");
        homePage.setPasswordField("");
        homePage.submit();

        String failedLoginMsg = "No matching userid/password";

        assertEquals(homePage.getTitle(), "Home");
        assertTrue(homePage.hasStringOnPage(failedLoginMsg));
        assertEquals("", homePage.getUseridField());
        assertEquals("", homePage.getPasswordField());
    }



    @After
    public void clean() {
        driver.quit();
    }
}