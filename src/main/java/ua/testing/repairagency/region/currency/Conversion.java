package ua.testing.repairagency.region.currency;

import org.springframework.context.i18n.LocaleContextHolder;
import ua.testing.repairagency.util.Constants;

public class Conversion {

    public long UsdToUahConvert(long price){
        return LocaleContextHolder.getLocale()
                .equals(Constants.UA_LOCALE)? price: price * Constants.CURRENCY_CONSTANT;
    }

    public long UahToUsdConvert(long price){
        return LocaleContextHolder.getLocale()
                .equals(Constants.EN_LOCALE)? price: price * Constants.CURRENCY_CONSTANT;
    }
}
