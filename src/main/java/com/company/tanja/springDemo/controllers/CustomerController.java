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
    /*
     This returns a specific customer, based on a given Id.
     We use a query string here to extract the Id.
     E.g. api/customer?id=ALFKI
    */
    @RequestMapping(value = "api/customer", method = RequestMethod.GET)
    public Customer getCustomerByQueryId(@RequestParam(value="id", defaultValue = "ALFKI") String id){
        return customerRepository.getCustomerById(id);
    }
    /*
     This returns a specific customer, based on a given Id.
     We use a header here to extract the Id.
    */
    @RequestMapping(value = "api/customerheader", method = RequestMethod.GET)
    public Customer getCustomerByHeaderId(@RequestHeader("id") String id){
        return customerRepository.getCustomerById(id);
    }
    /*
     This returns a specific customer, based on a given Id.
     We use a path variable here to extract the Id.
    */
    @RequestMapping(value = "api/customers/{id}", method = RequestMethod.GET)
    public Customer getCustomerByPathId(@PathVariable String id){
        return customerRepository.getCustomerById(id);
    }
    /*
     This adds a new customer.
     It takes the new customer from the body of the request.
    */
    @RequestMapping(value = "api/customers", method = RequestMethod.POST)
    public Boolean addNewCustomer(@RequestBody Customer customer){
        return customerRepository.addCustomer(customer);
    }
    /*
     This updates an existing customer.
     It takes the new customer data from the body of the request.
     As well as taking the Id of the customer from the path variables, this is
     to do a check if the body matches the id. Just a layer of saftey.
    */
    @RequestMapping(value = "api/customers/{id}", method = RequestMethod.PUT)
    public Boolean updateExistingCustomer(@PathVariable String id, @RequestBody Customer customer){
        return customerRepository.updateCustomer(customer);
    }


}
