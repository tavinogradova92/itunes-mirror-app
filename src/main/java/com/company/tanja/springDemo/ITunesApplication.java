package com.company.tanja.springDemo;
import com.company.tanja.springDemo.logging.LogToConsole;
import com.company.tanja.springDemo.models.Customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@SpringBootApplication
public class ITunesApplication {

	private static String URL = "jdbc:sqlite::resource:Northwind_small.sqlite";
	private static Connection conn = null;
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	private LogToConsole logger = new LogToConsole();

	public ArrayList<Customer> getAllCustomers(){
		ArrayList<Customer> customers = new ArrayList<>();
		try{
			// Connect to DB
			conn = DriverManager.getConnection(URL);
			logger.log("Connection to SQLite has been established.");

			// Make SQL query
			PreparedStatement preparedStatement =
					conn.prepareStatement("SELECT Id,CompanyName, ContactName,Phone FROM customer");
			// Execute Query
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customers.add(
						new Customer(
								resultSet.getString("Id"),
								resultSet.getString("CompanyName"),
								resultSet.getString("ContactName"),
								resultSet.getString("Phone")
						));
			}
			logger.log("Select all customers successful");
		}
		catch (Exception exception){
			logger.log(exception.toString());
		}
		finally {
			try {
				conn.close();
			}
			catch (Exception exception){
				logger.log(exception.toString());
			}
		}
		return customers;
	}


	public Customer getCustomerById(String custId){
		Customer customer = null;
		try{
			// Connect to DB
			conn = DriverManager.getConnection(URL);
			logger.log("Connection to SQLite has been established.");

			// Make SQL query
			PreparedStatement preparedStatement =
					conn.prepareStatement("SELECT Id,CompanyName, ContactName,Phone FROM customer WHERE Id = ?");
			preparedStatement.setString(1,custId);
			// Execute Query
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customer = new Customer(
						resultSet.getString("Id"),
						resultSet.getString("CompanyName"),
						resultSet.getString("ContactName"),
						resultSet.getString("Phone")
				);
			}
			logger.log("Select specific customer successful");
		}
		catch (Exception exception){
			logger.log(exception.toString());
		}
		finally {
			try {
				conn.close();
			}
			catch (Exception exception){
				logger.log(exception.toString());
			}
		}
		return customer;
	}

	public Boolean addCustomer(Customer customer){
		Boolean success = false;
		try{
			// Connect to DB
			conn = DriverManager.getConnection(URL);
			logger.log("Connection to SQLite has been established.");

			// Make SQL query
			PreparedStatement preparedStatement =
					conn.prepareStatement("INSERT INTO customer(Id,CompanyName,ContactName,Phone) VALUES(?,?,?,?)");
			preparedStatement.setString(1,customer.getCustomerId());
			preparedStatement.setString(2,customer.getCompanyName());
			preparedStatement.setString(3,customer.getContactName());
			preparedStatement.setString(4,customer.getPhone());
			// Execute Query
			int result = preparedStatement.executeUpdate();
			success = (result != 0);
			logger.log("Add customer successful");
		}
		catch (Exception exception){
			logger.log(exception.toString());
		}
		finally {
			try {
				conn.close();
			}
			catch (Exception exception){
				logger.log(exception.toString());
			}
		}
		return success;
	}

	public Boolean updateCustomer(Customer customer){
		Boolean success = false;
		try{
			// Connect to DB
			conn = DriverManager.getConnection(URL);
			logger.log("Connection to SQLite has been established.");

			// Make SQL query
			PreparedStatement preparedStatement =
					conn.prepareStatement("UPDATE customer SET Id = ?, CompanyName = ?, ContactName = ?, Phone = ? WHERE Id=?");
			preparedStatement.setString(1,customer.getCustomerId());
			preparedStatement.setString(2,customer.getCompanyName());
			preparedStatement.setString(3,customer.getContactName());
			preparedStatement.setString(4,customer.getPhone());
			preparedStatement.setString(5,customer.getCustomerId());
			// Execute Query
			int result = preparedStatement.executeUpdate();
			success = (result != 0);
			logger.log("Update customer successful");
		}
		catch (Exception exception){
			logger.log(exception.toString());
		}
		finally {
			try {
				conn.close();
			}
			catch (Exception exception){
				logger.log(exception.toString());
			}
		}
		return success;
	}


	public static void main(String[] args) {
		SpringApplication.run(ITunesApplication.class, args);
	}



}
