package ua.testing.repairagency.region.transliteration;

import com.ibm.icu.text.Transliterator;


public class Transliteration {
    private static final String CYRILLIC_TO_LATIN = "Ukrainian-Latin/BGN";


    public String transliterateToEng(String message) {
        return Transliterator.getInstance(CYRILLIC_TO_LATIN).transliterate(message);
    }

    public String transliterateToUa(String message) {
        return LatinToUkrainianTransliteration.transliterate(message);
    }

}
