package com.company.tanja.springDemo.data_access;

import com.company.tanja.springDemo.logging.LogToConsole;
import com.company.tanja.springDemo.models.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WebRequests {

    private static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private static Connection conn = null;
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    private LogToConsole logger = new LogToConsole();

    // getting five random artists
    public ArrayList<String> getRandomArtists() {
        ArrayList<String> randomArtists = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT name FROM artist ORDER BY RANDOM() LIMIT 5");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                randomArtists.add(
                                resultSet.getString("name")
                        );
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
        return randomArtists;
    }
}
