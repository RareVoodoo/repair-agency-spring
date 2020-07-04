package ua.testing.repairagency.region.transliteration;

import ua.testing.repairagency.util.Constants;

import java.util.*;


public class NameTransliteration {

    private Transliteration transliteration = new Transliteration();
    private Map<Locale, Transliterator> transliterationMap = new HashMap<>();

    {
        transliterationMap.put(Constants.EN_LOCALE, str -> transliteration.transliterateToEng(str));
        transliterationMap.put(Constants.UA_LOCALE, str -> transliteration.transliterateToUa(str));
    }

    public String transliterate(Locale locale, String text) {
        return transliterationMap.get(locale).transliterate(text);
    }

}
