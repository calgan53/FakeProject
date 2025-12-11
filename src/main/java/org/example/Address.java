package org.example;

import java.util.Objects;

public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;



    private static boolean isPostalCodeValid(String postalCode) {
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


    public Address(String postalCode, Province province, String city, String street, int streetNo) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        } else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
    }

    public enum Province {
        AB, BC, MB, NB, NL, NS, ON, PE, QC, SK
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNo= " + streetNo +
                ", street= '" + street + '\'' +
                ", city= '" + city + '\'' +
                ", province= " + province +
                ", postalCode= '" + postalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetNo == address.streetNo && Objects.equals(street, address.street) && Objects.equals(city, address.city) && province == address.province && Objects.equals(postalCode, address.postalCode);
    }

    public int getStreetNo() {
        return streetNo;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Province getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


}