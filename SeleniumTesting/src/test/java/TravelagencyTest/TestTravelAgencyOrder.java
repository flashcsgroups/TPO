package TravelagencyTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Никита on 17.12.2015.
 */
public class TestTravelAgencyOrder {
    private TravelagencyOrder order;

    @Before
    public void setUp() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        order = PageFactory.initElements(driver, TravelagencyOrder.class);
    }

    @After
    public void closeBrowser() {
        order.close();
    }

    @Test
    public void testValidInputs() {
        order.open();
        order.linkBecomePartners.click();
        order.linkTextOTA.click();
        order.textBoxTitle.clear();
        order.textBoxTitle.sendKeys("test");
        order.textBoxName.clear();
        order.textBoxName.sendKeys("test");
        order.textBoxPhone.clear();
        order.textBoxPhone.sendKeys("123456");
        order.xButton.click();

        Assert.assertTrue("Valid inputs don't pass", order.isTextPresent("Спасибо, ваша заявка принята"));
        order.close();
    }

    @Test
    public void testEmptyTitle() {
        order.open();
        order.linkBecomePartners.click();
        order.linkTextOTA.click();
        order.textBoxTitle.clear();
        //order.textBoxTitle.sendKeys("test");
        order.textBoxName.clear();
        order.textBoxName.sendKeys("test");
        order.textBoxPhone.clear();
        order.textBoxPhone.sendKeys("123456");
        order.xButton.click();

        Assert.assertFalse("Empty Title passed", order.isTextPresent("Спасибо, ваша заявка принята"));
        order.close();
    }
    @Test
    public void testEmptyName() {
        order.open();
        order.linkBecomePartners.click();
        order.linkTextOTA.click();
        order.textBoxTitle.clear();
        order.textBoxTitle.sendKeys("test");
        order.textBoxName.clear();
        //order.textBoxName.sendKeys("test");
        order.textBoxPhone.clear();
        order.textBoxPhone.sendKeys("123456");
        order.xButton.click();

        Assert.assertFalse("Empty Name passed", order.isTextPresent("Спасибо, ваша заявка принята"));
        order.close();
    }

    @Test
    public void testEmptyPhone() {
        order.open();
        order.linkBecomePartners.click();
        order.linkTextOTA.click();
        order.textBoxTitle.clear();
        order.textBoxTitle.sendKeys("test");
        order.textBoxName.clear();
        order.textBoxName.sendKeys("test");
        order.textBoxPhone.clear();
        //order.textBoxPhone.sendKeys("123456");
        order.xButton.click();

        Assert.assertFalse("Empty Phone passed", order.isTextPresent("Спасибо, ваша заявка принята"));
        order.close();
    }

    @Test
    public void testInvalidName() {
        order.open();
        order.linkBecomePartners.click();
        order.linkTextOTA.click();
        order.textBoxTitle.clear();
        order.textBoxTitle.sendKeys("test");
        order.textBoxName.clear();
        order.textBoxName.sendKeys("te%!$#!!5431st");
        order.textBoxPhone.clear();
        order.textBoxPhone.sendKeys("123456");
        order.xButton.click();

        Assert.assertFalse("Invalid name passed", order.isTextPresent("Спасибо, ваша заявка принята"));
        order.close();
    }

    @Test
    public void testInvalidPhone() {
        order.open();
        order.linkBecomePartners.click();
        order.linkTextOTA.click();
        order.textBoxTitle.clear();
        order.textBoxTitle.sendKeys("test");
        order.textBoxName.clear();
        order.textBoxName.sendKeys("test");
        order.textBoxPhone.clear();
        order.textBoxPhone.sendKeys("123sgwergewg456");
        order.xButton.click();

        Assert.assertFalse("Invalid phone passed", order.isTextPresent("Спасибо, ваша заявка принята"));
        order.close();
    }
}