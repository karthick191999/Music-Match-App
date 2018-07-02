package com.example.karthik.spider4.ArtistGet;

import com.example.karthik.spider4.TopArtists.Artist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karthik on 30-06-2018.
 */

public class Body {

    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}