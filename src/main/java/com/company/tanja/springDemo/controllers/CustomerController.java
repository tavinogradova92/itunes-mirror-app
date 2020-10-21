package com.company.tanja.springDemo.controllers;

import com.company.tanja.springDemo.ITunesApplication;
import com.company.tanja.springDemo.models.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController

public class CustomerController {

    private ITunesApplication customerRepository = new ITunesApplication();

    /*
     This first one just returns all the customers in the database
     it will return a CustomerShort object.
    */
    @RequestMapping(value="/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

}
