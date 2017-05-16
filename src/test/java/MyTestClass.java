import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
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
    public void createLetter()  {
        browser.findElement(By.cssSelector("a.b-toolbar__btn.js-shortcut")).click();
        browser.findElement(By.cssSelector("textarea.js-input.compose__labels__input")).sendKeys("test_user13052017@mail.ru");
        browser.findElement(By.cssSelector("input.b-input")).sendKeys("TEST");

        List<WebElement> iframes = browser.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            if (iframe.getAttribute("id").contains("composeEditor_ifr")) {
                browser.switchTo().frame(iframe);
                WebElement element = browser.findElement(By.cssSelector("Body"));
                element.sendKeys("TEST MESSAGE");

            }
        }
        browser.switchTo().defaultContent();

        browser.findElement(By.cssSelector("div[data-name=\"send\"]")).click();

        sleep(10);
        browser.findElement(By.cssSelector("a[class=\"b-nav__link js-shortcut\"]")).click();
        sleep(30);
       Assert.assertTrue(checkIfLetterExists("a[class=\"js-href b-datalist__item__link\" "), "The letter has not been sent!");
    }
    public static boolean checkIfLetterExists(String selector) {
        try {
            browser.findElement(By.cssSelector(selector));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}