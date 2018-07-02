package com.example.karthik.spider4;

/**
 * Created by karthik on 02-07-2018.
 */

public class ArtistD {
String artistName;
    String artistRating;
    String artistGenre;

    public ArtistD(String artistName, String artistRating, String artistGenre) {
        this.artistName = artistName;
        this.artistRating = artistRating;
        this.artistGenre = artistGenre;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistRating() {
        return artistRating;
    }

    public void setArtistRating(String artistRating) {
        this.artistRating = artistRating;
    }

    public String getArtistGenre() {
        return artistGenre;
    }

    public void setArtistGenre(String artistGenre) {
        this.artistGenre = artistGenre;
    }
}
