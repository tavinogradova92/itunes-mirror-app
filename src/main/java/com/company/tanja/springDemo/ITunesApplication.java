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

	private static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
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
					conn.prepareStatement("SELECT customerId, firstName, lastName, country, postalCode, phone, email FROM customer");
			// Execute Query
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customers.add(
						new Customer(
								resultSet.getInt("customerId"),
								resultSet.getString("firstName"),
								resultSet.getString("lastName"),
								resultSet.getString("country"),
								resultSet.getString("postalCode"),
								resultSet.getString("phone"),
								resultSet.getString("email")
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

	public Boolean addCustomer(Customer customer){
		Boolean success = false;
		try{
			// Connect to DB
			conn = DriverManager.getConnection(URL);
			logger.log("Connection to SQLite has been established.");

			// Make SQL query
			PreparedStatement preparedStatement =
					conn.prepareStatement("INSERT INTO customer(customerId, firstName, lastName, country, postalCode, phone, email) VALUES(?,?,?,?,?,?,?)");
			preparedStatement.setInt(1,customer.getCustomerId());
			preparedStatement.setString(2,customer.getFirstName());
			preparedStatement.setString(3,customer.getLastName());
			preparedStatement.setString(4,customer.getCountry());
			preparedStatement.setString(5,customer.getPostalCode());
			preparedStatement.setString(6,customer.getPhone());
			preparedStatement.setString(7,customer.getEmail());
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
					conn.prepareStatement("UPDATE customer SET customerId = ?, firstName = ?, lastName = ?, country = ?, postalCode = ?, phone = ?, email = ? WHERE customerId=?");
			preparedStatement.setInt(1,customer.getCustomerId());
			preparedStatement.setString(2,customer.getFirstName());
			preparedStatement.setString(3,customer.getLastName());
			preparedStatement.setString(4,customer.getCountry());
			preparedStatement.setString(5,customer.getPostalCode());
			preparedStatement.setString(6,customer.getPhone());
			preparedStatement.setString(7,customer.getEmail());
			preparedStatement.setInt(8,customer.getCustomerId());
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
