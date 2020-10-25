package com.company.tanja.springDemo.data_access;

import com.company.tanja.springDemo.models.CustomerFavouriteGenre;
import com.company.tanja.springDemo.models.CustomersSpendingMax;
import com.company.tanja.springDemo.logging.LogToConsole;
import com.company.tanja.springDemo.models.Customer;
import com.company.tanja.springDemo.models.CustomersPerCountry;

import java.sql.*;
import java.util.ArrayList;


public class CustomerAPIRequests {

    private static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private static Connection conn = null;
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    private LogToConsole logger = new LogToConsole();

    // getting the list of all the customers as an array
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
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
        } catch (Exception exception) {
            logger.log(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logger.log(exception.toString());
            }
        }
        return customers;
    }

    // adding a new customer to the array
    public Boolean addCustomer(Customer customer) {
        Boolean success = false;
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO customer(customerId, firstName, lastName, country, postalCode, phone, email) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setString(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());
            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
            logger.log("Add customer successful");
        } catch (Exception exception) {
            logger.log(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logger.log(exception.toString());
            }
        }
        return success;
    }

    // updating data of an existing customer in an array
    public Boolean updateCustomer(Customer customer) {
        Boolean success = false;
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE customer SET customerId = ?, firstName = ?, lastName = ?, country = ?, postalCode = ?, phone = ?, email = ? WHERE customerId=?");
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setString(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setInt(8, customer.getCustomerId());
            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
            logger.log("Update customer successful");
        } catch (Exception exception) {
            logger.log(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logger.log(exception.toString());
            }
        }
        return success;
    }

    // counting the number of customers per country in a descending order
    public ArrayList<CustomersPerCountry> countAmountPerCountry() {
        ArrayList<CustomersPerCountry> customerPerCountry = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT country, COUNT(*) AS numberByCountry FROM customer GROUP BY country ORDER BY numberByCountry DESC");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerPerCountry.add(
                        new CustomersPerCountry(
                                resultSet.getString("country"),
                                resultSet.getInt("numberByCountry")
                        ));
            }
            logger.log("Show amount of customers per country done successfully");
        } catch (Exception exception) {
            logger.log(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logger.log(exception.toString());
            }
        }
        return customerPerCountry;
    }

    // choosing how much each of the customer spent in a descending order
    public ArrayList<CustomersSpendingMax> countSumSpentPerCustomer() {
        ArrayList<CustomersSpendingMax> customerSpendingMax = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT customer.customerId, customer.firstName, customer.lastName, invoice.total FROM customer LEFT JOIN invoice ON customer.customerId = invoice.customerId ORDER BY invoice.total DESC");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerSpendingMax.add(
                        new CustomersSpendingMax(
                                resultSet.getInt("customerId"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getInt("total")
                        ));
            }
            logger.log("Show amount of customers per country done successfully");
        } catch (Exception exception) {
            logger.log(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logger.log(exception.toString());
            }
        }
        return customerSpendingMax;
    }

    // The function returns the customer's most popular genre (or two if both are popular)
    public CustomerFavouriteGenre getCustomersFavouriteGenre(int customerId) {
        CustomerFavouriteGenre favouriteGenres = null;
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");
            // Make SQL query
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT customerId, FirstName, LastName, Name AS Genre " +
                            "FROM (SELECT Customer.CustomerId, Customer.FirstName, LastName, Genre.Name, " +
                            "COUNT(Genre.Name) AS Number FROM Customer " +
                            "LEFT JOIN Invoice on Customer.CustomerId = Invoice.CustomerId " +
                            "LEFT JOIN InvoiceLine on Invoice.InvoiceId = InvoiceLine.InvoiceId " +
                            "LEFT JOIN Track on Track.TrackId = InvoiceLine.TrackId " +
                            "LEFT JOIN Genre on Genre.GenreId = Track.GenreId " +
                            "WHERE Customer.CustomerId = ? " +
                            "GROUP BY Genre.Name " +
                            "ORDER BY Number DESC) " +
                            "WHERE Number = (SELECT MAX(Number) FROM (SELECT Genre.Name, COUNT(Genre.Name) " +
                            "AS Number FROM Customer " +
                            "LEFT JOIN Invoice on Customer.CustomerId = Invoice.CustomerId " +
                            "LEFT JOIN InvoiceLine on Invoice.InvoiceId = InvoiceLine.InvoiceId " +
                            "LEFT JOIN Track on Track.TrackId = InvoiceLine.TrackId " +
                            "LEFT JOIN Genre on Genre.GenreId = Track.GenreId " +
                            "WHERE Customer.CustomerId = ? " +
                            "GROUP BY Genre.Name " +
                            "ORDER BY Number DESC))");
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                favouriteGenres = new CustomerFavouriteGenre(resultSet.getInt("customerId"), resultSet.getString("FirstName"), resultSet.getString("LastName"), new ArrayList<>());
            }

            do {
                favouriteGenres.genres.add(resultSet.getString("Genre"));
            } while (resultSet.next());

        } catch (Exception e) {
            System.out.println("Something went wrong...");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Something went wrong while closing connection.");
                e.printStackTrace();
            }
        }
        return favouriteGenres;
    }

}