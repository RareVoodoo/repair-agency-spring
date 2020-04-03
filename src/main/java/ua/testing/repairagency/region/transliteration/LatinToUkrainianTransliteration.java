package ua.testing.repairagency.region.transliteration;

import java.util.HashMap;
import java.util.Map;

public class LatinToUkrainianTransliteration {
    private static  Map<String, String> letters = new HashMap<>();

    static {
        letters.put("Shch", "Щ");
        letters.put("shch", "щ");
        letters.put("zgh", "зг");
        letters.put("tsk", "цьк");
        letters.put("yye", "ие");
        letters.put("oye", "оє");
        letters.put("nky\\b", "ньки");
        letters.put("iie", "іє");
        letters.put("’ye", "`є");
        letters.put("’ya", "`я");
        letters.put("’yi", "`ї");
        letters.put("pie", "п`є");
        letters.put("skyi\\b", "ський");
        letters.put("\bZgh", "Зг");
        letters.put("\\Byi", "иї");
        letters.put("\\Bmia", "м'я");
        letters.put("\\Baie", "ає");
        letters.put("\\Bkhai\\B", "хай");
        letters.put("aiv\\b", "аїв");
        letters.put("ie", "'\\'є");
        letters.put("sk", "ськ");
        letters.put("Zh", "Ж");
        letters.put("zh", "ж");
        letters.put("Kh", "Х");
        letters.put("kh", "х");
        letters.put("Ts", "Ц");
        letters.put("ts", "ц");
        letters.put("Ch", "Ч");
        letters.put("ch", "ч");
        letters.put("Sh", "Ш");
        letters.put("sh", "ш");
        letters.put("iu", "ю");
        letters.put("ia", "я");
        letters.put("oi", "ої");
        letters.put("yo", "йо");
        letters.put("ya", "я");
        letters.put("yi\\B", "ї");
        letters.put("\bYe", "Є");
        letters.put("\bye", "є");
        letters.put("\bYi", "Ї");
        letters.put("\byi", "ї");
        letters.put("\bYu", "Ю");
        letters.put("Ya", "Я");
        letters.put("ii\b", "ій");
        letters.put("yi\b", "ий");
        letters.put("yy\b", "ий");
        letters.put("\bY", "Й");
        letters.put("А", "A");
        letters.put("a", "а");
        letters.put("B", "Б");
        letters.put("b", "б");
        letters.put("V", "В");
        letters.put("v", "в");
        letters.put("H", "Г");
        letters.put("h", "г");
        letters.put("G", "Ґ");
        letters.put("g", "ґ");
        letters.put("D", "Д");
        letters.put("d", "д");
        letters.put("E", "Е");
        letters.put("e", "е");
        letters.put("Z", "З");
        letters.put("z", "з");
        letters.put("Y", "И");
        letters.put("y", "и");
        letters.put("I", "І");
        letters.put("i", "і");
        letters.put("K", "К");
        letters.put("k", "к");
        letters.put("L", "Л");
        letters.put("l", "л");
        letters.put("M", "М");
        letters.put("m", "м");
        letters.put("N", "Н");
        letters.put("n", "н");
        letters.put("O", "О");
        letters.put("o", "о");
        letters.put("P", "П");
        letters.put("p", "п");
        letters.put("R", "Р");
        letters.put("r", "р");
        letters.put("S", "С");
        letters.put("s", "с");
        letters.put("T", "Т");
        letters.put("t", "т");
        letters.put("U", "У");
        letters.put("у", "u");
        letters.put("F", "Ф");
        letters.put("f", "ф");
        letters.put("’", "`");
    }


    public static String transliterate(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            String l = text.substring(i, i + 1);
            sb.append(letters.getOrDefault(l, l));
        }
        return sb.toString();

    }
}
