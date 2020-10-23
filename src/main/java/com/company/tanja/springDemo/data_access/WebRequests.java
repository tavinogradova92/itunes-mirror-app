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
            logger.log("Select five random artists successful");
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

    // getting five random artists
    public ArrayList<String> getRandomSongs() {
        ArrayList<String> randomSongs = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT name FROM track ORDER BY RANDOM() LIMIT 5");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                randomSongs.add(
                        resultSet.getString("name")
                );
            }
            logger.log("Select five random songs successful");
        } catch (Exception exception) {
            logger.log(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logger.log(exception.toString());
            }
        }
        return randomSongs;
    }

    // getting five random genres
    public ArrayList<String> getRandomGenres() {
        ArrayList<String> randomGenres = new ArrayList<>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT name FROM genre ORDER BY RANDOM() LIMIT 5");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                randomGenres.add(
                        resultSet.getString("name")
                );
            }
            logger.log("Select five random genres successful");
        } catch (Exception exception) {
            logger.log(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logger.log(exception.toString());
            }
        }
        return randomGenres;
    }
}
