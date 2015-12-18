package OTAOrderTest;

import com.testcase.hotelorder.HotelOrderTest.HotelOrder;
import OTAOrderTest.OTAOrder;
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
public class TestOTAOrder {
    private OTAOrder otaOrder;

    @Before
    public void setUp() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        otaOrder = PageFactory.initElements(driver, OTAOrder.class);
    }

    @After
    public void closeBrowser() {
        otaOrder.close();
    }

    @Test
    public void testValidInputs() {
        otaOrder.open();
        otaOrder.linkBecomePartners.click();
        otaOrder.linkTextOTA.click();
        otaOrder.textBoxTitle.clear();
        otaOrder.textBoxTitle.sendKeys("test");
        otaOrder.textBoxName.clear();
        otaOrder.textBoxName.sendKeys("test");
        otaOrder.textBoxPhone.clear();
        otaOrder.textBoxPhone.sendKeys("123456");
        otaOrder.xButton.click();

        Assert.assertTrue("Valid inputs don't pass", otaOrder.isTextPresent("Спасибо, ваша заявка принята"));
        otaOrder.close();
    }

    @Test
    public void testEmptyTitle() {
        otaOrder.open();
        otaOrder.linkBecomePartners.click();
        otaOrder.linkTextOTA.click();
        otaOrder.textBoxTitle.clear();
        //otaOrder.textBoxTitle.sendKeys("test");
        otaOrder.textBoxName.clear();
        otaOrder.textBoxName.sendKeys("test");
        otaOrder.textBoxPhone.clear();
        otaOrder.textBoxPhone.sendKeys("123456");
        otaOrder.xButton.click();

        Assert.assertFalse("Empty Title passed", otaOrder.isTextPresent("Спасибо, ваша заявка принята"));
        otaOrder.close();
    }
    @Test
    public void testEmptyName() {
        otaOrder.open();
        otaOrder.linkBecomePartners.click();
        otaOrder.linkTextOTA.click();
        otaOrder.textBoxTitle.clear();
        otaOrder.textBoxTitle.sendKeys("test");
        otaOrder.textBoxName.clear();
        //otaOrder.textBoxName.sendKeys("test");
        otaOrder.textBoxPhone.clear();
        otaOrder.textBoxPhone.sendKeys("123456");
        otaOrder.xButton.click();

        Assert.assertFalse("Empty Name passed", otaOrder.isTextPresent("Спасибо, ваша заявка принята"));
        otaOrder.close();
    }

    @Test
    public void testEmptyPhone() {
        otaOrder.open();
        otaOrder.linkBecomePartners.click();
        otaOrder.linkTextOTA.click();
        otaOrder.textBoxTitle.clear();
        otaOrder.textBoxTitle.sendKeys("test");
        otaOrder.textBoxName.clear();
        otaOrder.textBoxName.sendKeys("test");
        otaOrder.textBoxPhone.clear();
        //otaOrder.textBoxPhone.sendKeys("123456");
        otaOrder.xButton.click();

        Assert.assertFalse("Empty Phone passed", otaOrder.isTextPresent("Спасибо, ваша заявка принята"));
        otaOrder.close();
    }

    @Test
    public void testInvalidName() {
        otaOrder.open();
        otaOrder.linkBecomePartners.click();
        otaOrder.linkTextOTA.click();
        otaOrder.textBoxTitle.clear();
        otaOrder.textBoxTitle.sendKeys("test");
        otaOrder.textBoxName.clear();
        otaOrder.textBoxName.sendKeys("te%!$#!!5431st");
        otaOrder.textBoxPhone.clear();
        otaOrder.textBoxPhone.sendKeys("123456");
        otaOrder.xButton.click();

        Assert.assertFalse("Invalid name passed", otaOrder.isTextPresent("Спасибо, ваша заявка принята"));
        otaOrder.close();
    }

    @Test
    public void testInvalidPhone() {
        otaOrder.open();
        otaOrder.linkBecomePartners.click();
        otaOrder.linkTextOTA.click();
        otaOrder.textBoxTitle.clear();
        otaOrder.textBoxTitle.sendKeys("test");
        otaOrder.textBoxName.clear();
        otaOrder.textBoxName.sendKeys("test");
        otaOrder.textBoxPhone.clear();
        otaOrder.textBoxPhone.sendKeys("123sgwergewg456");
        otaOrder.xButton.click();

        Assert.assertFalse("Invalid phone passed", otaOrder.isTextPresent("Спасибо, ваша заявка принята"));
        otaOrder.close();
    }
}
