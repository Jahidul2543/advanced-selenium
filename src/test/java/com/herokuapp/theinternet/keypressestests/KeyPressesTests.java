package com.herokuapp.theinternet.keypressestests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.KeyPressesPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.Key;

public class KeyPressesTests extends TestUtilities {

    /**
     * 1. (WelcomePage) Go to KeyPresses Page from welcome page - Click Key Presses link in welcome page
     * 2. (KeyPress) Press the key
     * 3. Get the value
     * 4. Verify - Assert the actual value with expected value
     * 5. The End
     * */
    @Test
    public void pressKeyTest(){
        log.info("Start of the pressKeyTest");
        WelcomePage welcomePage = new WelcomePage(driver, log);
        KeyPressesPage keyPressesPage = new KeyPressesPage(driver,log);

        welcomePage.clickKeyPresesLInk();
        keyPressesPage.pressKey(Keys.TAB);
        String actualText = keyPressesPage.getActualText();

        Assert.assertTrue(actualText.equals("You entered: TAB"), "Result is not expected");


    }
}
