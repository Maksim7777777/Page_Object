package Pages;

import Data.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CardTopUpPage {

    private SelenideElement topUpHeading = $x("//h1");
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement cardFrom = $("[data-test-id='from'] input");
    private SelenideElement topUpButton = $("[data-test-id='action-transfer']");

    public CardTopUpPage() {
        topUpHeading.shouldBe(Condition.visible);
    }


    public DashboardPage shouldTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        cardFrom.setValue(cardInfo.getCardNumber());
        topUpButton.click();
    }
}