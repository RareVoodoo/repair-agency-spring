package ua.testing.repairagency.region.transliteration;

import java.util.*;


public class NameTransliteration {
    public static final Locale UA_LOCALE = new Locale("ua");
    public static final Locale EN_LOCALE = Locale.US;

    private Transliteration transliteration = new Transliteration();
    private Map<Locale, Transliterator> transliterationMap = new HashMap<>();


    {
        transliterationMap.put(EN_LOCALE, str -> transliteration.transliterateToEng(str));
        transliterationMap.put(UA_LOCALE, str -> transliteration.transliterateToUa(str));
    }


    public String transliterate(Locale locale, String text) {
        return transliterationMap.get(locale).transliterate(text);
    }

}
