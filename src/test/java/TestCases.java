import org.testng.annotations.Test;

public class TestCases extends Config {

    @Test(priority = 2)
    protected void successLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.successLoginToMailRu();
    }

    @Test(priority = 1)
    protected void failureLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.failureLoginToMailRu();
    }

    @Test(priority = 3, dependsOnMethods = "successLoginTest")
    protected  void createLetter(){
        CreateLetter letter = new CreateLetter(driver);
        letter.createLetter();
    }
}
