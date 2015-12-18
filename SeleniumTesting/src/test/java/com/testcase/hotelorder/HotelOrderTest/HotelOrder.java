package com.testcase.hotelorder.HotelOrderTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Никита on 17.12.2015.
 */
public class HotelOrder {
    public static final String url = "http://www.travelline.ru/";
    private WebDriver driver;

    @FindBy(linkText = "Стать партнером")
    public WebElement linkBecomePartners;

    @FindBy(id = "form_checkbox_products_and_services_146")
    public WebElement checkBoxUniqueSite;

    @FindBy(id = "form_checkbox_products_and_services_147")
    public WebElement checkBoxTemplateSite;

    @FindBy(id = "form_text_40")
    public WebElement textBoxName;

    @FindBy(id = "form_text_13")
    public WebElement textBoxPhone;

    @FindBy(id = "form_text_22")
    public WebElement textBoxEMail;

    @FindBy(id = "form_text_12")
    public WebElement textBoxHotelName;

    @FindBy(linkText = "Отправить заявку на сотрудничество")
    public WebElement linkedTextSend;

    @FindBy(xpath = "(//a[contains(@href, '#')])[4]")
    public WebElement wfs;

    public void open() {
        driver.get(url);
    }

    public HotelOrder(WebDriver driver) {
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
