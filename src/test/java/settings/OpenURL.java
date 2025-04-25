package settings;

import com.codeborne.selenide.Selenide;

public class OpenURL {
    public OpenURL(String url) {
        Selenide.open(url);
    }
}
