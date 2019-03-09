package steps;

import pages.QASectionPage;

public class QASectionPageSteps {

    QASectionPage qaSectionPage;

    public QASectionPageSteps(QASectionPage qaSectionPage){
        this.qaSectionPage = qaSectionPage;
    }

    public boolean takeSurvey(){
        qaSectionPage.fillAnswersInSurvey();
        qaSectionPage.clickOnButtonSubmitOnSurvey();

        return qaSectionPage.isSurveyResultsSubmitted();
    }
}
