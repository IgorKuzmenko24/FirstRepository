import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MyTestClass {


    protected static WebDriver browser;


    protected static void sleep(int time) {
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @BeforeClass
    public void startChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32\\chromedriver.exe");
        browser = new ChromeDriver();

        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        browser.get("http://mail.ru");
    }

    @AfterClass
    public void closeBrowser() {
        browser.quit();
    }

    @Test(priority = 1, description = "Successful login into Mail.ru")
    public void login() {
        browser.findElement(By.cssSelector("#mailbox__login")).sendKeys("test_user13052017@mail.ru");
        browser.findElement(By.cssSelector("#mailbox__password")).sendKeys("password12345");
        browser.findElement(By.cssSelector("#mailbox__auth__button")).click();
        sleep(5);
        Assert.assertTrue(browser.getTitle().contains("Mail"), "Could not log out!");

    }

    @Test(priority = 2, description = "Create a letter", dependsOnMethods = "login")
    public void createLetter() throws InterruptedException {
        browser.findElement(By.cssSelector("a.b-toolbar__btn.js-shortcut")).click();
        browser.findElement(By.cssSelector("textarea.js-input.compose__labels__input")).sendKeys("kuzmenko.igor23@gmail.com");
        browser.findElement(By.cssSelector("input.b-input")).sendKeys("TEST");

        //browser.switchTo().frame("toolkit-149468988972438composeEditor_ifr");
       // browser.findElement(By.cssSelector("#toolkit-149468988972438composeForm > div.compose__editor")).sendKeys("Hello");
        //browser.switchTo().defaultContent();
       // browser.findElement(By.xpath("//*[@id=\"tinymce\"]")).click();
        //browser.findElement(By.cssSelector("div.b-toolbar__btn.b-toolbar__btn_.b-toolbar__btn_false.js-shortcut")).click();


    }
}