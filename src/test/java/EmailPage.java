import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailPage {
    WebDriver driver;
    //Конструктор для работы PageFactory()
    public EmailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    //Локация необходимых элементов по xpath, css и тд
    @FindBy(xpath = "//span[text()=\"Написать\"]")
    public WebElement newEmailBtnText;

    @FindBy(xpath = "//a[contains(@class,\"Button2\")]")
    public WebElement newEmailBtn;

    @FindBy(css = "div[aria-label=\"Кому\"]")
    private WebElement addresseeField;

    @FindBy(xpath = "//*[@id=\"compose-field-subject-4\"]")
    private WebElement themeField;

    @FindBy(xpath = "//*[@id=\"cke_1_contents\"]/div")
    private WebElement emailBody;

    @FindBy(xpath = "//span[text()=\"Письмо отправлено\"]")
    private WebElement maleSent;

    // Здесь и далее ожидание для прогрузки страницы до начала  действий
    public String checkNewEmailBtnText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Написать\"]")));
        return newEmailBtnText.getText();
    }

    public void setAddressee(String addressee) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label=\"Кому\"]")));
        addresseeField.sendKeys(addressee);
    }

    public void setTheme(String theme) {
        themeField.sendKeys(theme);
    }

    public void setEmailBody(String text) {
        emailBody.sendKeys(text);
    }

    public String checkMailSent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Письмо отправлено\"]")));
        return maleSent.getText();
    }

}
