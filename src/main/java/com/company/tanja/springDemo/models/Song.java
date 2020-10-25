package com.company.tanja.springDemo.models;

public class Song {

    private String trackName;
    private String artistName;
    private String albumTitle;
    private String genreName;


    public Song() {
    }

    public Song(String trackName, String artistName, String albumTitle, String genreName) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumTitle = albumTitle;
        this.genreName = genreName;
    }


    public String getSongName() {
        return trackName;
    }

    public void setSongName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumTitle;
    }

    public void setAlbumName(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getGenre() {
        return genreName;
    }

    public void setGenre(String genreName) {
        this.genreName = genreName;
    }
}
