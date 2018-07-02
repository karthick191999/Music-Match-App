package com.example.karthik.spider4.TopArtists;

/**
 * Created by karthik on 30-06-2018.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistAliasList {

    @SerializedName("artist_alias")
    @Expose
    private String artistAlias;

    public String getArtistAlias() {
        return artistAlias;
    }

    public void setArtistAlias(String artistAlias) {
        this.artistAlias = artistAlias;
    }

}

