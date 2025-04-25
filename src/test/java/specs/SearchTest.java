package specs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.SearchPage;
import settings.OpenURL;
import settings.SetBrowser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {
    private final SearchPage sp = new SearchPage();

    private final static String URL = "https://www.farpost.ru/vladivostok/";
    private final static String TEXT = "iphone";
    private final static String EXPECTED_SEARCH_URL = "https://www.farpost.ru/vladivostok/tech/communication/cellphones/+/Apple+iPhone/?_suggest=1&query=iphone";


    @BeforeEach
    public void openBrowser() {
        SetBrowser setBrowser = new SetBrowser();
        setBrowser.init();

        new OpenURL(URL);

        sp.inputText(TEXT);
        sp.waitUntilPageSubjectIsVisible();
    }

    @AfterEach
    public void tearDown() {
        SetBrowser setBrowser = new SetBrowser();
        setBrowser.ClearBrowser();
    }

    @Test
    @DisplayName("Verify that the search page is opened with the valid URL")
    public void checkSearchPageURLIsCorrect() {
        String cut_url = sp.getPageAddressWithoutHash();

        assertEquals(EXPECTED_SEARCH_URL, cut_url);
    }

    @Test
    @DisplayName("Verify Suggested List block is present on search page")
    public void checkSuggestionListIsDisplay() {

        assertTrue(sp.visibilitySuggestionList());
    }

    @Test
    @DisplayName("Verify the suggestions list displays 3 elements")
    public void checkSuggestionsListHasThreeElements() {
        String lenght = sp.getLengthSuggestionsList();

        assertEquals("3", lenght);
    }

    @Test
    @DisplayName("Verify the first suggestion is active")
    public void checkFirstElementIsActiveInSuggestList() {
        sp.getPageAddressWithoutHash(); //Заменить на ожидание элемента. Подумать какого.

        assertTrue(sp.getActiveElementInSuggestionsList(0));
    }

}
