package steps;

import io.qameta.allure.Step;
import org.junit.Assert;
import pages.QASectionPage;

public class QASectionPageSteps {

    @Step("Click on 'Resend Email' button")
    public static void resendEmail(WrikeHomePageSteps wrikeHomePageSteps) {
        QASectionPage qaSectionPage = wrikeHomePageSteps.createNewAccount();
        qaSectionPage.clickOnResendEmailButton();

        Assert.assertTrue("'Resend Email' button isn't pressed", qaSectionPage.isResendButtonClicked());
    }

    @Step("Submit completed survey")
    public static void takeSurvey(WrikeHomePageSteps wrikeHomePageSteps) {
        //Go to survey page
        QASectionPage qaSectionPage = wrikeHomePageSteps.createNewAccount();

        qaSectionPage.fillAnswersInSurvey();
        qaSectionPage.clickOnButtonSubmitOnSurvey();

        Assert.assertTrue("Survey results isn't submitted", qaSectionPage.isSurveyResultsSubmitted());

    }
}
