package ua.testing.repairagency.region;

import com.ibm.icu.text.Transliterator;


public class Transliteration {
    public static final String CYRILLIC_TO_LATIN = "Ukrainian-Latin/BGN";


    public String transliterateToEng(String message) {
        Transliterator transliterator = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return transliterator.transliterate(message);
    }

    public String transliterateToUa(String message) {
        return LatinToUkrainianTransliteration.transliterate(message);
    }

}
