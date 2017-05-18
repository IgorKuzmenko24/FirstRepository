import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;
interface DataForLetter{
     String ADDRESS = "test_user13052017@mail.ru";
     String SubjectOfLetter = "TEST";
     By writeLetterButton = cssSelector("a.b-toolbar__btn.js-shortcut");
     By addrField = cssSelector("textarea.js-input.compose__labels__input");
     By subjField = cssSelector("input.b-input");
     String testText = "TEST MESSAGE";
     By sendButton = cssSelector("div[data-name=\"send\"]");
     By listSendLetters = cssSelector("a[class=\"b-nav__link js-shortcut\"]");

}
public class CreateLetter implements DataForLetter {

    protected WebDriver driver;

    public CreateLetter(WebDriver driver) {
        this.driver = driver;
    }

    public void createLetter() {
        driver.findElement(writeLetterButton).click();
        driver.findElement(addrField).sendKeys(ADDRESS);
        driver.findElement(subjField).sendKeys(SubjectOfLetter);

        //then we need switch to iframe in order to write a text
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            if (iframe.getAttribute("id").contains("composeEditor_ifr")) {
                driver.switchTo().frame(iframe);
                WebElement element = driver.findElement(cssSelector("Body"));
                element.sendKeys(testText);

            }
        }

        driver.switchTo().defaultContent();
        driver.findElement(sendButton).click();
        Config.sleep(10);
        driver.findElement(listSendLetters).click();
        Config.sleep(10);
        Assert.assertTrue(checkLetterSend(), "The letter has not been sent!");
    }

    public boolean checkLetterSend() {
        try {
            List<WebElement> elements = driver.findElements(By.className("b-datalist__body"));
            String addr = elements.get(elements.size() - 1).findElement(By.className("b-datalist__item__addr")).getText();
            if (addr.equals(ADDRESS)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


}
