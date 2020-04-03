package ua.testing.repairagency.region.currency;

import org.springframework.context.i18n.LocaleContextHolder;
import ua.testing.repairagency.region.transliteration.NameTransliteration;

public class Conversion {
    private final static long CURRENCY_CONSTANT=  30;

    public long UsdToUahConvert(long price){
        return LocaleContextHolder.getLocale()
                .equals(NameTransliteration.UA_LOCALE)? price: price * CURRENCY_CONSTANT;
    }

    public long UahToUsdConvert(long price){
        return LocaleContextHolder.getLocale()
                .equals(NameTransliteration.EN_LOCALE)? price: price * CURRENCY_CONSTANT;
    }
}
