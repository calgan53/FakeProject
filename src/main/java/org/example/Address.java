package org.example;

public class Address {
    int streetNo;
    String street;
    String city;
    Province province; // enum, contains abbriviations of all provinces in Canada
    String postalCode;

    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null) {
            return false;
        }
        if (postalCode.length() != 6) {
            return false;
        }
        for (int i = 0; i < 6; i++) {
            char ch = postalCode.charAt(i);

            if (i % 2 == 0) {
                if (!Character.isLetter(ch)) {
                    return false;
                }
            } else {
                if (!Character.isDigit(ch)) {
                    return false;
                }
            }
        }
        return true;
    }



    public enum Province {
        AB, BC, MB, NB, NL, NS, ON, PE, QC, SK
    }
}