package StageTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Никита on 17.12.2015.
 */
public class Stage {
    public static final String url = "http://www.travelline.ru/";
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Стажировка в TravelLine')]")
    public WebElement linkStage;

    @FindBy(id = "form_text_137")
    public WebElement textBoxName;

    @FindBy(name = "form_file_138")
    public WebElement buttonAdd;

    @FindBy(xpath = "//a[contains(text(),'Отправить заявку')]")
    public WebElement buttonSend;

    public void open() {
        driver.get(url);
    }

    public Stage(WebDriver driver) {
        this.driver = driver;
    }

    public void close() {
        driver.quit();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isTextPresent(String text) {
        boolean result = false;
        String data = driver.getPageSource();
        result = data.contains(text);
        return result;
    }
}