package Pages;


import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");
   /* private SelenideElement card1TopUpButton = $x("//*[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']/button");
    private SelenideElement card2TopUpButton = $x("//*[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']/button");*/



    public DashboardPage() {
        heading.shouldBe(visible);
    }

    /*public CardTopUpPage clicksOnCard() {
        card1TopUpButton.click();
        return new CardTopUpPage();
    }*/

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(text(cardInfo.getCardNumber().substring(15))).getText();
        return extractBalance(text);
    }

    public CardTopUpPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(text(cardInfo.getCardNumber().substring(15))).$("button").click();
        return new CardTopUpPage();
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


}
