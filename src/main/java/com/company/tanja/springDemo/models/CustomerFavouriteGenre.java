package com.company.tanja.springDemo.models;

import java.util.ArrayList;

public class CustomerFavouriteGenre {

    public int customerId;
    public String firstName;
    public String lastName;
    public ArrayList<String> genres;

    public CustomerFavouriteGenre(int customerId, String firstName, String lastName, ArrayList<String> genres) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genres = genres;
    }


}
