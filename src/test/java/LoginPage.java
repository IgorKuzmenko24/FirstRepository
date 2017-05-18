import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import static org.openqa.selenium.By.cssSelector;

interface DataForLog{
    By emailField = cssSelector("#mailbox__login");
    By passwordField = cssSelector("#mailbox__password");
    By enterButton = cssSelector("#mailbox__auth__button");
    String USERNAME = "test_user13052017@mail.ru";
    String PASSWORD = "password12345";
    String WRONG_PASSWORD = "Password11as";
}

public class LoginPage implements DataForLog {


    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

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
