package com.company.tanja.springDemo.models;

public class CustomersSpendingMax {

    private int customerId;
    private String firstName;
    private String lastName;
    private int total;


    public CustomersSpendingMax() {
    }

    public CustomersSpendingMax(int customerId, String firstName, String lastName, int total) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.total = total;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
