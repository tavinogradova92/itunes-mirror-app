package com.company.tanja.springDemo.data_access;

import com.company.tanja.springDemo.logging.LogToConsole;
import com.company.tanja.springDemo.models.Song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WebRequests {

    private static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private static Connection conn = null;
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

    // creating search request
    public ArrayList<Song> getSongsFoundList(String searchQuery) {
        ArrayList<Song> searchedTracks = new ArrayList<Song>();
        try {
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT track.name AS trackName, album.title AS albumTitle, artist.name AS artistName, genre.name AS genreName FROM track INNER JOIN album ON track.albumId = album.albumId INNER JOIN artist ON album.artistId = artist.artistId INNER JOIN genre ON track.genreID = genre.genreId WHERE track.name LIKE ?");
            preparedStatement.setString(1, "%" + searchQuery + "%");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                searchedTracks.add(
                        new Song(
                                resultSet.getString("trackName"),
                                resultSet.getString("artistName"),
                                resultSet.getString("albumTitle"),
                                resultSet.getString("genreName")
                        ));
            }
            logger.log("Song list created");
        } catch (Exception exception) {
            logger.log(exception.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logger.log(exception.toString());
            }
        }
        return searchedTracks;
    }
}
