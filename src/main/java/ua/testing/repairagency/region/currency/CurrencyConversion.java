package ua.testing.repairagency.region.currency;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static ua.testing.repairagency.region.transliteration.NameTransliteration.EN_LOCALE;
import static ua.testing.repairagency.region.transliteration.NameTransliteration.UA_LOCALE;

public class CurrencyConversion {
    private Conversion conversion = new Conversion();
    private Map<Locale, CurrencyConverter> conversionMap = new HashMap<>();


    {
        conversionMap.put(EN_LOCALE, price -> conversion.UahToUsdConvert(price));
        conversionMap.put(UA_LOCALE, price -> conversion.UsdToUahConvert(price));
    }


    public long convert(Locale locale, long price) {
        return conversionMap.get(locale).convert(price);
    }
}
