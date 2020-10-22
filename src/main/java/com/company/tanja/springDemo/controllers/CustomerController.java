package com.company.tanja.springDemo.controllers;

import com.company.tanja.springDemo.ITunesApplication;
import com.company.tanja.springDemo.models.Customer;
import com.company.tanja.springDemo.models.CustomersPerCountry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController

public class CustomerController {

    private ITunesApplication customerRepository = new ITunesApplication();


    // This function just returns all the customers in the database
    @RequestMapping(value="/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    // This adds a new customer. It takes the new customer from the body of the request.
    @RequestMapping(value="/api/customers", method = RequestMethod.POST)
    public Boolean addNewCustomer(@RequestBody Customer customer){
        return customerRepository.addCustomer(customer);
    }

    /* This updates an existing customer.
     It takes the new customer data from the body of the request.
     As well as taking the Id of the customer from the path variables, this is
     to do a check if the body matches the id. */
    @RequestMapping(value = "/api/customers/{id}", method = RequestMethod.PUT)
    public Boolean updateExistingCustomer(@PathVariable String id, @RequestBody Customer customer){
        return customerRepository.updateCustomer(customer);
    }

    // This function returns the amount of customers per country in a descending order
    @RequestMapping(value="/api/customers/country", method = RequestMethod.GET)
    public ArrayList<CustomersPerCountry> countAmountPerCountry(){
        return customerRepository.countAmountPerCountry();
    }

    // This function returns the amount of money spent by each customer in a descending order
    @RequestMapping(value="/api/customers/spent", method = RequestMethod.GET)
    public ArrayList<CustomersSpendingMax> countSumSpentPerCustomer(){
        return customerRepository.countSumSpentPerCustomer();
    }


}
