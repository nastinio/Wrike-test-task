package pages;

import config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WrikeHomePage {

    private SeleniumConfig config;

    public WrikeHomePage(SeleniumConfig config) {
        this.config = config;
        PageFactory.initElements(this.config.getDriver(), this);
    }

    @FindBy(css = ".wg-header__free-trial-form:nth-child(1) em")
    private WebElement buttonGetStartedForFree;

    @FindBy(css = ".modal-form-trial__form")
    private WebElement formAction;

    @FindBy(css = ".modal-form-trial__input")
    private WebElement inputOnFormAction;

    @FindBy(css = ".modal-form-trial__submit")
    private WebElement submitButtonOnFormAction;

    //@FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div[1]/div")
    @FindBy(css = ".wg-footer__group.wg-footer__group--social")
    private WebElement panelFollowUs;

    private WebElement buttonTwitter;


    public String getPageTitle() {
        return config.getDriver().getTitle();
    }

    public void navigate() {
        this.config.navigateTo("http://www.wrike.com/");
    }

    public String getURL(){
        return this.config.getDriver().getCurrentUrl();
    }


    // Methods for creating a new account
    public void clickOnGetStartedForFree() {
        config.clickElement(buttonGetStartedForFree);
    }

    public void enterRandomEmail() {
        inputOnFormAction.clear();
        inputOnFormAction.sendKeys(generateEmail());
    }

    public QASectionPage clickSubmitOnFormAction() {
        config.clickElement(submitButtonOnFormAction);

        QASectionPage qaSectionPage = new QASectionPage(config);
        PageFactory.initElements(config.getDriver(), qaSectionPage);

        return qaSectionPage;
    }

    // Auxiliary methods for generating random mail
    private String generateEmail() {
        String mask = "+wpt@wriketask.qaa";

        int length = ThreadLocalRandom.current().nextInt(3, 6);
        String randomText = generateRandomText(length);

        return randomText + mask;
    }

    private String generateRandomText(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    // Methods for checking twitter button
    public boolean containsFollowUsTwitterButton() {
        buttonTwitter = panelFollowUs.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a"));
        return buttonTwitter.isDisplayed();
    }

    public String getTwitterButtonLink() {
        return buttonTwitter.getAttribute("href");
    }

    public boolean isTwitterButtonIconCorrect() {
        return buttonTwitter.findElement(By.tagName("use")).getAttribute("xlink:href").equals("/content/themes/wrike/dist/img/sprite/vector//footer-icons.symbol.svg?v1#twitter");

    }


}
