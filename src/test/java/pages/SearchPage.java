package pages;

import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.valueOf;

public class SearchPage {
    private final String INPUT_FIELD = "//input[@id='search']"; // локатор строки поиска
    private final String SEARCH_BTN = "//input[@value='Найти']"; // локатор кнопки "Найти"
    private final String PAGE_SUBJECT = "//h1[contains(@class,'js-viewdir-subject')]";
    private final String SUGGESTIONS_LIST = "//*[contains(@class,'dir-search-suggest__item')]";
    private final String ACTIVE_ELEMENT = "dir-search-suggest__item_active";

    // Методы
    // Метод ввода текста в поисковую строку
    public void inputText (String text) {
        $x(INPUT_FIELD).setValue(text);
        $x(SEARCH_BTN).click();
    }

    //Отображение Списка предложений
    public boolean visibilitySuggestionList() {
        return  $x(SUGGESTIONS_LIST).is(visible);
    }

    //Метод определения видимости элемента
    public void waitUntilPageSubjectIsVisible (){
        $x(PAGE_SUBJECT).shouldBe(visible);
    }

    //Метод получения адресной строки без хеша
    public String getPageAddressWithoutHash(){
        return WebDriverRunner.url().split("#")[0];
    }

    //Метод получения длины списка предложений - элементов
    public String getLengthSuggestionsList() {
        $$x(SUGGESTIONS_LIST).shouldHave(sizeGreaterThan(0));
       return valueOf($$x(SUGGESTIONS_LIST).size());
    }

    //Метод проверки, что элемент в списке активный
    public boolean getActiveElementInSuggestionsList(int index) {
        $$x(SUGGESTIONS_LIST).get(index).shouldHave(cssClass(ACTIVE_ELEMENT));
        return true;
    }

}
