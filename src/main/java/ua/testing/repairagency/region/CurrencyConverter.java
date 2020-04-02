package ua.testing.repairagency.region;


public class CurrencyConverter {
    private final static long CURRENCY_CONSTANT=  30;

    public long UsdToUahConvert(long price){
        return price * CURRENCY_CONSTANT;
    }
    public long UahToUsdConvert(long price){
            return price / CURRENCY_CONSTANT;
    }
}
