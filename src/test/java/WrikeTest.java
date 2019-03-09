import config.SeleniumConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.QASectionPage;
import pages.WrikeHomePage;
import steps.QASectionPageSteps;
import steps.WrikeHomePageSteps;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

public class WrikeTest {
    private SeleniumConfig config;

    private WrikeHomePageSteps wrikeHomePageSteps;
    private QASectionPage qaSectionPage;

    @Before
    public void setUp() {
        config = new SeleniumConfig();
        wrikeHomePageSteps = new WrikeHomePageSteps(config);
    }

    @After
    public void teardown() {
        config.close();
    }

    @Test
    public void createMyWrikeAccount() {
        /*
        * Open url: wrike.com;
        * Click "Get started for free" button near "Login" button;
        * Fill in the email field with random generated value of email with mask “<random_text>+wpt@wriketask.qaa”;
        * Click on "Create my Wrike account" button + check with assertion that you are moved to the next page;
        * */

        String expectedResult = "Thank you for choosing Wrike!";
        String result = wrikeHomePageSteps.createNewAccount().getPageTitle();

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void checkThatAnswersAreSubmitted(){
        /*
        * Fill in the Q&A section at the left part of page (like random generated answers)
        * + check with assertion that your answers are submitted;
        * */

        qaSectionPage = wrikeHomePageSteps.createNewAccount();
        QASectionPageSteps qaSectionPageSteps = new QASectionPageSteps(qaSectionPage);
        // Run the survey scenario
        Assert.assertTrue(qaSectionPageSteps.takeSurvey());

    }

    @Test
    public void resendEmailTest(){
        /*
        * Click on "Resend email" + check it with assertion;
        * */

        qaSectionPage = wrikeHomePageSteps.createNewAccount();
        qaSectionPage.clickOnResendEmailButton();

        Assert.assertTrue(qaSectionPage.isResendButtonClicked());

    }

    @Test
    public void checkTwitterButton(){
        /*
        * Check that section "Follow us" at the site footer contains the "Twitter" button
        * that leads to the correct url and has the correct icon;
        * */

        Assert.assertTrue(wrikeHomePageSteps.checkTwitterButton());

    }


}
