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
					conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM customer");
			// Execute Query
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customers.add(
						new Customer(
								resultSet.getInt("CustomerId"),
								resultSet.getString("FirstName"),
								resultSet.getString("LastName"),
								resultSet.getString("Country"),
								resultSet.getString("PostalCode"),
								resultSet.getString("Phone"),
								resultSet.getString("Email")
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



	public static void main(String[] args) {
		SpringApplication.run(ITunesApplication.class, args);
	}



}
