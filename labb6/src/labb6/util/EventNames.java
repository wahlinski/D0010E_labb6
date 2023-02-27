package labb6.util;

public enum EventNames {
    START, ANKOMST, PLOCK, BETALNING, STÄNGER, STOP;

    @Override
    public String toString() {
        String s = super.toString();
        char first = s.charAt(0);
        return first + s.substring(1).toLowerCase();
    }
}
