import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AuthPage {
    WebDriver driver;
    //Конструктор для работы PageFactory()
    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/button")
    public WebElement enterAccountBtn;

    @FindBy(xpath = "//*[@id=\"passp-field-login\"]")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\"passp:sign-in\"]")
    public WebElement nextToPasswordBtn;

    @FindBy(xpath = "//*[@id=\"passp-field-passwd\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"passp:sign-in\"]")
    public WebElement loginBtn;

    public void setLogin(String login) {
        loginField.sendKeys(login);
    }

    public void clickBtn(WebElement element) {
        element.click();
    }
    //ожидание для прогрузки страницы до начала  действий
    public void setPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"passp-field-passwd\"]")));
        passwordField.sendKeys(password);
    }
}

