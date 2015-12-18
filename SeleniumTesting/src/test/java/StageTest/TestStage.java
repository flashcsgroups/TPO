package StageTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by Никита on 17.12.2015.
 */
public class TestStage {

    private Stage stage;

    @Before
    public void setUp() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        stage = PageFactory.initElements(driver, Stage.class);
    }

    @After
    public void closeBrowser() {
        stage.close();
    }

    @Test
    public void validInput() throws InterruptedException, AWTException {
        stage.open();
        stage.linkStage.click();
        stage.textBoxName.sendKeys("FIO");
        stage.buttonAdd.click();

        StringSelection ss = new StringSelection("D:\\STUD\\V\\TPO\\SeleniumTesting\\test.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        stage.buttonSend.click();
        Assert.assertTrue("Valid inputs didn't pass", stage.isTextPresent("Спасибо, ваша заявка принята"));
        stage.close();
    }
}
