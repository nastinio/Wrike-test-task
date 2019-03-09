package steps;

import config.SeleniumConfig;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.QASectionPage;
import pages.WrikeHomePage;

public class WrikeHomePageSteps {
    WrikeHomePage wrikeHomePage;

    public WrikeHomePageSteps(SeleniumConfig config) {
        wrikeHomePage = new WrikeHomePage(config);
    }

    public QASectionPage createNewAccount() {
        wrikeHomePage.navigate();
        wrikeHomePage.clickOnGetStartedForFree();
        wrikeHomePage.enterRandomEmail();
        return wrikeHomePage.clickSubmitOnFormAction();
    }

    @Step("Create my Wrike account")
    public void checkCreatingWrikeAccount() {
        String expectedResult = "Thank you for choosing Wrike!";
        String result = createNewAccount().getPageTitle();

        Assert.assertEquals("Moving to next page failed", expectedResult, result);
    }


    @Step("Check Twitter button")
    public void checkTwitterButton() {
        wrikeHomePage.navigate();

        // Check that 'Follow Us' panel contains Twitter button
        Assert.assertTrue("'Follow Us' panel doesn't contain Twitter button", wrikeHomePage.containsFollowUsTwitterButton());
        // Check that Twitter button's URL is correct
        Assert.assertTrue("Twitter button's URL isn't correct", wrikeHomePage.getTwitterButtonLink().equals("https://twitter.com/wrike"));
        // Check that Twitter button's icon is correct
        Assert.assertTrue("Twitter button's icon isn't correct", wrikeHomePage.isTwitterButtonIconCorrect());
    }


}
