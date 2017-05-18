import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.openqa.selenium.By.cssSelector;

public class LoginPage {

    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private String USERNAME = "test_user13052017@mail.ru";
    private String PASSWORD = "password12345";
    private String WRONG_PASSWORD = "Password11as";
    private By emailField = cssSelector("#mailbox__login");
    private By passwordField = cssSelector("#mailbox__password");
    private By enterButton = cssSelector("#mailbox__auth__button");


    private void loginToMail(String password) {
        driver.findElement(emailField).sendKeys(USERNAME);
        driver.findElement(passwordField).sendKeys(PASSWORD);
        driver.findElement(enterButton).click();
        Config.sleep(5);
    }
    protected void successLoginToMailRu(){
        loginToMail(PASSWORD);
        Assert.assertTrue(driver.getTitle().contains("Mail"), "Could not log out!");
    }
    protected void failureLoginToMailRu(){
        loginToMail(WRONG_PASSWORD);
        Assert.assertTrue(!driver.getTitle().contains("Mail"), "Could not log out!");
    }




}
