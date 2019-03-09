package steps;

import config.SeleniumConfig;
import pages.QASectionPage;
import pages.WrikeHomePage;

public class WrikeHomePageSteps {
    WrikeHomePage wrikeHomePage;

    public WrikeHomePageSteps(SeleniumConfig config){
        wrikeHomePage = new WrikeHomePage(config);

    }

    public QASectionPage createNewAccount(){
        wrikeHomePage.navigate();
        wrikeHomePage.clickOnGetStartedForFree();
        wrikeHomePage.enterRandomEmail();
        return wrikeHomePage.clickSubmitOnFormAction();
    }

    public boolean checkTwitterButton(){
        wrikeHomePage.navigate();

        boolean containsFollowUsTwitterButton = wrikeHomePage.containsFollowUsTwitterButton();
        boolean isURLCorrect = wrikeHomePage.getTwitterButtonLink().equals("https://twitter.com/wrike");
        boolean isTwitterButtonIconCorrect = wrikeHomePage.isTwitterButtonIconCorrect();

        return containsFollowUsTwitterButton&&isURLCorrect&&isTwitterButtonIconCorrect;
    }


}
