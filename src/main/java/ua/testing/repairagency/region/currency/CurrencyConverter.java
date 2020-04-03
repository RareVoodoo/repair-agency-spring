package ua.testing.repairagency.region.currency;

@FunctionalInterface
public interface CurrencyConverter {
    long convert(long price);
}

