package OTAOrderTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Никита on 17.12.2015.
 */
public class OTAOrder {
    public static final String url = "http://www.travelline.ru/";
    private WebDriver driver;

    @FindBy(linkText = "Стать партнером")
    public WebElement linkBecomePartners;

    @FindBy(linkText = "ОТА и туроператорам")
    public WebElement linkTextOTA;

    @FindBy(id = "form_text_77")
    public WebElement textBoxTitle;

    @FindBy(id = "form_text_85")
    public WebElement textBoxName;

    @FindBy(id = "form_text_78")
    public WebElement textBoxPhone;

    @FindBy(xpath = "(//a[contains(text(),'Отправить заявку на сотрудничество')])[2]")
    public WebElement xButton;

    public void open() {
        driver.get(url);
    }

    public OTAOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void close() {
        driver.quit();
    }

    public String getTitle()
    {
        return driver.getTitle();
    }

    public boolean isTextPresent(String text)
    {
        boolean result=false;
        String data = driver.getPageSource();
        result = data.contains(text);
        return result;
    }
}
