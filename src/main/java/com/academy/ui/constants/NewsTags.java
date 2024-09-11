package com.academy.ui.constants;

public enum NewsTags {
    NEWS("News", "Новини"),
    EVENTS("Events", "Події"),
    EDUCATION("Education", "Освіта"),
    INITIATIVES("Initiatives", "Ініціативи"),
    ADS("Ads", "Реклама");

    private final String englishText;
    private final String ukrainianText;
    private final String key;

    NewsTags(String englishText, String ukrainianText) {
        this.key = englishText;
        this.englishText = englishText;
        this.ukrainianText = ukrainianText;
    }

    public String getText(String languageCode) {
        switch (languageCode) {
            case "uk":
                return ukrainianText;
            case "en":
            default:
                return englishText;
        }
    }

    public String getTagKey(String key) {
        return key;
    }

    public static NewsTags[] getAllTagNames() {
        return values();
    }
}
