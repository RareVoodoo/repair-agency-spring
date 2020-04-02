package ua.testing.repairagency.region;

import java.util.*;


public class NameTransliteration {
    public static final Locale uaLocale = new Locale("ua");
    public static final Locale enLocale = Locale.US;

    private Transliteration transliteration = new Transliteration();
    private Map<Locale, Transliterator> letters = new HashMap<>();


    {
        letters.put(enLocale, (str) -> transliteration.transliterateToEng(str));
        letters.put(uaLocale, (str) -> transliteration.transliterateToUa(str));
    }


    public String transliterate(Locale locale, String text) {
        return letters.get(locale).transliterate(text);
    }

}
