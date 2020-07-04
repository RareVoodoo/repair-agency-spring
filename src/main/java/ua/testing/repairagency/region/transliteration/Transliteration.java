package ua.testing.repairagency.region.transliteration;

import com.ibm.icu.text.Transliterator;
import ua.testing.repairagency.util.Constants;


public class Transliteration {


    public String transliterateToEng(String message) {
        return Transliterator.getInstance(Constants.CYRILLIC_TO_LATIN).transliterate(message);
    }

    public String transliterateToUa(String message) {
        return LatinToUkrainianTransliteration.transliterate(message);
    }

}
