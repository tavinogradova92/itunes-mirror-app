package com.company.tanja.springDemo.models;

public class Customer {
    private String customerId;
    private String companyName;
    private String contactName;
    private String phone;

    public Customer() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer(String customerId, String companyName, String contactName, String phone) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.phone = phone;
    }


}
