package com.company.tanja.springDemo.controllers;

import com.company.tanja.springDemo.data_access.CustomerAPIRequests;
import com.company.tanja.springDemo.models.Customer;
import com.company.tanja.springDemo.models.CustomerFavouriteGenre;
import com.company.tanja.springDemo.models.CustomersPerCountry;
import com.company.tanja.springDemo.models.CustomersSpendingMax;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController

public class CustomerController {

    private CustomerAPIRequests customerRepository = new CustomerAPIRequests();

    // This function returns all the customers in the database
    @RequestMapping(value="/api/customers", method = RequestMethod.GET)
    public ArrayList<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    // This adds a new customer. It takes the new customer from the body of the request.
    @RequestMapping(value="/api/customers", method = RequestMethod.POST)
    public Boolean addNewCustomer(@RequestBody Customer customer){
        return customerRepository.addCustomer(customer);
    }

    // This updates an existing customer.
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
    @RequestMapping(value="/api/customers/total_sum", method = RequestMethod.GET)
    public ArrayList<CustomersSpendingMax> countSumSpentPerCustomer(){
        return customerRepository.countSumSpentPerCustomer();
    }

    // The function returns the customer's most popular genre (or two if both are popular)
    @RequestMapping(value="/customers/{customerId}/popular/genre", method = RequestMethod.GET)
    public CustomerFavouriteGenre getFavouriteGenre(@PathVariable int customerId) {
        return customerRepository.getCustomersFavouriteGenre(customerId);
    }


}
