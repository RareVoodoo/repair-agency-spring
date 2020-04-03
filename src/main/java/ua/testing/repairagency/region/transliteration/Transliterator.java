package ua.testing.repairagency.region.transliteration;

@FunctionalInterface
public interface Transliterator {
    String transliterate(String str);
}
