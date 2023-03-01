package labb6.util;

/**
 * The Enumeration EventName handels important Events and makes them more accessible .
 * @author Abdi Abdi, Viggo Härdelin, Filip Wåhlin, Samuel Melander.
 */
public enum EventName {
    START, ANKOMST, PLOCK, BETALNING, STÄNGER, STOP;

    @Override
    public String toString() {
        String s = super.toString();
        char first = s.charAt(0);
        return first + s.substring(1).toLowerCase();
    }
}
