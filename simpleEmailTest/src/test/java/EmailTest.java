import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class EmailTest {
    public static WebDriver webDriver;
    public static AuthPage authPage;
    public static EmailPage emailPage;

    public void clickBtn(WebElement el) {
        el.click();
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver.exe", TestProperties.getProperty("webdriver"));
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(TestProperties.getProperty("authpage"));
        authPage = new AuthPage(webDriver);
        emailPage = new EmailPage(webDriver);
    }

    @Test
    public void EmailTest() {
        //Кликаем Кнопку входа в аккаунт
        clickBtn(authPage.enterAccountBtn);
        //Вводим валидный логин
        authPage.setLogin(TestProperties.getProperty("login"));
        //Переход к заполнению пароля
        clickBtn(authPage.nextToPasswordBtn);
        //Вводим валидный пароль
        authPage.setPassword(TestProperties.getProperty("password"));
        //Входим в почту
        clickBtn(authPage.loginBtn);
        //Наличие возможности написать письмо означает успешный вход в акк
        Assert.assertEquals("Написать", emailPage.checkNewEmailBtnText());
        //Кликаем кнопку создания нового письма
        clickBtn(emailPage.newEmailBtn);
        //вставляем адресат, тему и тело письма
        emailPage.setAddressee(TestProperties.getProperty("addressee"));
        emailPage.setTheme(TestProperties.getProperty("theme"));
        emailPage.setEmailBody(TestProperties.getProperty("mail-text"));
        //Быстрые команды для отправки письма
        new Actions(webDriver).keyDown(Keys.CONTROL).keyDown(Keys.ENTER).perform();
        //Проверка отправки письма
        Assert.assertEquals("Письмо отправлено", emailPage.checkMailSent());
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }
}
