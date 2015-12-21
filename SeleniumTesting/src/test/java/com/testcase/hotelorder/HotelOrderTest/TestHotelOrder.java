package com.testcase.hotelorder.HotelOrderTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Никита on 14.12.2015.
 */
public class TestHotelOrder {
    private WebDriver driver;
    private HotelOrder hotelOrder;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        hotelOrder = PageFactory.initElements(driver, HotelOrder.class);
    }

    @After
    public void closeBrowser() {
        hotelOrder.close();
    }

    @Test
    public void testValidInputs() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxUniqueSite.click();
        hotelOrder.textBoxName.sendKeys("test");
        hotelOrder.textBoxPhone.sendKeys("158611");
        hotelOrder.textBoxEMail.sendKeys("afasfsafa@asgag.srg");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertTrue("Valid inputs don't pass", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testOppositeItems() throws InterruptedException {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxUniqueSite.click();
        Thread.sleep(10000);
        hotelOrder.checkBoxTemplateSite.click();
        Thread.sleep(10000);
        hotelOrder.textBoxName.sendKeys("test");
        hotelOrder.textBoxPhone.sendKeys("158611");
        hotelOrder.textBoxEMail.sendKeys("afasfsafa@asgag.srg");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertFalse("Opposite items passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testEmptyItem() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.textBoxName.sendKeys("test");
        hotelOrder.textBoxPhone.sendKeys("158611");
        hotelOrder.textBoxEMail.sendKeys("afasfsafa@asgag.srg");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertFalse("Empty items passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testEmptyName() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxTemplateSite.click();
        hotelOrder.textBoxPhone.sendKeys("158611");
        hotelOrder.textBoxEMail.sendKeys("afasfsafa@asgag.srg");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertFalse("Empty name passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testEmptyPhone() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxUniqueSite.click();
        hotelOrder.textBoxName.sendKeys("test");
        hotelOrder.textBoxEMail.sendKeys("afasfsafa@asgag.srg");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertFalse("Empty phone passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testEmptyEmail() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxUniqueSite.click();
        hotelOrder.textBoxName.sendKeys("test");
        hotelOrder.textBoxPhone.sendKeys("158611");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertFalse("Empty Email passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testEmptyHotel() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxUniqueSite.click();
        hotelOrder.textBoxName.sendKeys("test");
        hotelOrder.textBoxPhone.sendKeys("158611");
        hotelOrder.textBoxEMail.sendKeys("afasfsafa@asgag.srg");
        hotelOrder.wfs.click();

        Assert.assertFalse("Empty hotel name passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testInvalidName() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxUniqueSite.click();
        hotelOrder.textBoxName.sendKeys("te1-1%st");
        hotelOrder.textBoxPhone.sendKeys("158611");
        hotelOrder.textBoxEMail.sendKeys("afasfsafa@asgag.srg");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertFalse("Invalid name passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testInvalidPhone() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxUniqueSite.click();
        hotelOrder.textBoxName.sendKeys("test");
        hotelOrder.textBoxPhone.sendKeys("testtest");
        hotelOrder.textBoxEMail.sendKeys("afasfsafa@asgag.srg");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertFalse("Invalid phone passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }

    @Test
    public void testInvalidEmail() {
        hotelOrder.open();
        hotelOrder.linkBecomePartners.click();
        hotelOrder.checkBoxUniqueSite.click();
        hotelOrder.textBoxName.sendKeys("test");
        hotelOrder.textBoxPhone.sendKeys("158611");
        hotelOrder.textBoxEMail.sendKeys("afasfsafaasgagsrg");
        hotelOrder.textBoxHotelName.sendKeys("test");
        hotelOrder.wfs.click();

        Assert.assertFalse("Invalid Email passed", hotelOrder.isTextPresent("Спасибо, ваша заявка принята"));
        hotelOrder.close();
    }
}
