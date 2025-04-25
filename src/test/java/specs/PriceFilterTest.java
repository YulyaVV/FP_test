package specs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.FilterPage;
import pages.SearchPage;
import settings.OpenURL;
import settings.SetBrowser;

import static org.junit.jupiter.api.Assertions.*;

public class PriceFilterTest {
    private final SearchPage sp = new SearchPage();
    private final FilterPage fp = new FilterPage();

    private final static String URL = "https://www.farpost.ru/vladivostok/";
    private final static String TEXT = "iphone";
    private final static String EXPECTED_FILTR_URL = "https://www.farpost.ru/vladivostok/tech/communication/cellphones/+/Apple+iPhone/?price_max=1000&query=iphone";


    /*
   index=1 - поле ввода цены
   price = 1000 - макс.цена
    */
    @BeforeEach
    public void openBrowser() {
        SetBrowser setBrowser = new SetBrowser();
        setBrowser.init();

        new OpenURL(URL);

        sp.inputText(TEXT);
        fp.inputPrice(1, 1000);
        fp.waitElement();
    }

    @AfterEach
    public void tearDown() {
        SetBrowser setBrowser = new SetBrowser();
        setBrowser.ClearBrowser();
    }

    @Test
    @DisplayName("Verify URL after applying Price filter")
    public void checkFilterPageURLIsCorrectAfterPriceFilter() {
        String cut_url = sp.getPageAddressWithoutHash();

        assertEquals(EXPECTED_FILTR_URL, cut_url);
    }

    @Test
    @DisplayName("Verify Suggestions block is not displayed when filter is applied")
    public void checkSuggestionListIsHiddenAfterPriceFilter() {

        assertFalse(sp.visibilitySuggestionList());
    }

    @Test
    @DisplayName("Verify no prices exceed specified maxPrice value") //тут надо какое-то ожидание видимо добавить, тест не проходит
    public void checkPriceInBulletinAfterPriceFilter(){

        assertTrue(fp.verifyThatAllPricesBelow(1000));
    }
}
