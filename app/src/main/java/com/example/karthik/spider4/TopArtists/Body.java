package com.example.karthik.spider4.TopArtists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by karthik on 25-06-2018.
 */
public class Body {

    @SerializedName("artist_list")
    @Expose
    private List<ArtistList> artistList = null;

    public List<ArtistList> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<ArtistList> artistList) {
        this.artistList = artistList;
    }

}
