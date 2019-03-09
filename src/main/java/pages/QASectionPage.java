package pages;

import config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QASectionPage {

    private SeleniumConfig config;
    public QASectionPage(SeleniumConfig config) {
        this.config = config;
    }

    @FindBy(css = ".submit")
    private WebElement buttonSubmitOnSurveyForm;

    @FindBy(css = ".survey")
    private WebElement surveyForm;

    //@FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[contains(@class,'radio')]")
    @FindBy(css = ".radio")
    private List<WebElement> answersFields;

    @FindBy(css = ".wg-btn.wg-btn--white.wg-btn--hollow.button.js-button")
    private WebElement buttonResendEmail;

    public String getPageTitle(){
        config.wait.until(ExpectedConditions.urlToBe("https://www.wrike.com/resend/"));
        return this.config.getDriver().getTitle();
    }


    // Methods for filling survey fields
    private int getCountQuestions(){
        return answersFields.size();
    }

    private int getCountAnswersInCurrentQuestion(int numberCurrentQuestion){
        //numberCurrentAnswer - start from 1
        List<WebElement> buttonsAnswerInCurrentQuestion = config.getDriver().findElements(By.xpath(
                "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[contains(@class,'radio')]["+numberCurrentQuestion+"]/label/button"));
        return buttonsAnswerInCurrentQuestion.size();
    }

    private int generateRandomAnswer(int countAnswers){
        return ThreadLocalRandom.current().nextInt(1, countAnswers);
    }

    private void clickOnAnswerButton(int question,int answer){
        WebElement buttonAnswer = config.getDriver().findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/" +
                "div[contains(@class,'radio')]["+question+"]/label["+answer+"]/button"));
        config.clickElement(buttonAnswer);
    }

    public void fillAnswersInSurvey(){
        int countQuestion = getCountQuestions();

        for(int i = 1;i<=countQuestion;i++){
            int countAnswersInCurrentQuestion = getCountAnswersInCurrentQuestion(i);
            int numberRandomGeneratedAnswer = generateRandomAnswer(countAnswersInCurrentQuestion);
            clickOnAnswerButton(i,numberRandomGeneratedAnswer);
        }
    }


    // Methods for submit survey
    public void clickOnButtonSubmitOnSurvey(){
        config.clickElement(buttonSubmitOnSurveyForm);
    }

    public boolean isSurveyResultsSubmitted(){
        try {
            config.wait.until(ExpectedConditions.invisibilityOf(buttonSubmitOnSurveyForm));
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }


    // Methods for checking "Resend email" button
    public void clickOnResendEmailButton(){
        config.wait.until(ExpectedConditions.visibilityOf(buttonResendEmail));
        config.clickElement(buttonResendEmail);
    }

    public boolean isResendButtonClicked(){
        try {
            config.wait.until(ExpectedConditions.invisibilityOf(buttonResendEmail));
            return true;
        }catch (WebDriverException e){
            return false;
        }
    }


}
