package com.company.tanja.springDemo.models;

public class CustomersPerCountry {
    private String country;
    private Integer numberByCountry;

    public CustomersPerCountry() {
    }

    public CustomersPerCountry(String country, Integer numberByCountry) {
        this.country = country;
        this.numberByCountry = numberByCountry;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumberByCountry() {
        return numberByCountry;
    }

    public void setNumberByCountry(Integer numberByCountry) {
        this.numberByCountry = numberByCountry;
    }
}
