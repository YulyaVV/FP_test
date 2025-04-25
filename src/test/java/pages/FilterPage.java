package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FilterPage {
    private final String PRICE_TAB = "//a[@data-url-label='cena']";
    private final String PRICE_FIELDS = "//div[@data-name='price']//input[@type='text']";
    private final String PRICES_IN_BULLETINS= "//*[@data-role='price']";
    private final String FILTER_NAME = "//span[@class='filterVal' and @data-filter-name='price']";
    private final String SUBSCRIBE_BTN = "//div[contains(@class,'js-saved-search-plate')]";

    //Метод клика по вкладке Цена
    public void clickPriceBtn() {
        $x(PRICE_TAB).shouldBe(visible)
                .click();
    }

    //Метод ввода цены
    public void inputPrice(int index, int price) {
        clickPriceBtn();
        $$x(PRICE_FIELDS).get(index)
                .setValue(String.valueOf(price))
                .pressEnter();
    }


    //Метод получения списка элементов с ценами
    public ElementsCollection getListOfElementsWithPrice() {
        ElementsCollection priceElements = $$x(PRICES_IN_BULLETINS);
        return priceElements;
    }

    //Метод сравнения цен с заданным числом
    public boolean verifyThatAllPricesBelow(int maxPrice){
        for (WebElement priceElement : getListOfElementsWithPrice()) {                                   // Находит все элементы с ценами
            double price = getNumericPriceOnly(priceElement);                                            // Извлекает числовое значение цены
            //System.out.println(1);
            if (price >  maxPrice) {                                                                  // Если цена > maxPrice — возвращает false
                System.out.println("Превышение лимита: " + price + " > " + maxPrice);
                return false;
            }
        }
        return true;                                                                                // Если все цены <= maxPrice — возвращает true
    }

    //Метод извлечения цены без символа "Р" и пробелов
    private double getNumericPriceOnly(WebElement priceElement) {
       String priceText = priceElement.getText()
                .replaceAll("[^0-9.,]", "") //удаляем символы
                .replace(",", "."); //удаляем пробелы
        return Double.parseDouble(priceText);
    }

    //Метод тест
    public void waitElement() {
        $x(FILTER_NAME).shouldBe(visible);
        $x(SUBSCRIBE_BTN).shouldBe(visible)
                .click();
    }



}
