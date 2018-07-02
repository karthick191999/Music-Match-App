package com.example.karthik.spider4.TopArtists;

import com.example.karthik.spider4.TopTracks.PrimaryGenres;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by karthik on 25-06-2018.
 */
public class Artist {

    @SerializedName("artist_id")
    @Expose
    private Integer artistId;
    @SerializedName("artist_mbid")
    @Expose
    private String artistMbid;
    @SerializedName("artist_name")
    @Expose
    private String artistName;
    @SerializedName("artist_alias_list")
    @Expose
    private List<ArtistAliasList> artistAliasList = null;
    @SerializedName("artist_rating")
    @Expose
    private Integer artistRating;
    @SerializedName("updated_time")
    @Expose
    private String updatedTime;
    @SerializedName("primary_genres")
    @Expose
    private PrimaryGenres primaryGenres;

    public PrimaryGenres getPrimaryGenres() {
        return primaryGenres;
    }

    public void setPrimaryGenres(PrimaryGenres primaryGenres) {
        this.primaryGenres = primaryGenres;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtistMbid() {
        return artistMbid;
    }

    public void setArtistMbid(String artistMbid) {
        this.artistMbid = artistMbid;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<ArtistAliasList> getArtistAliasList() {
        return artistAliasList;
    }

    public void setArtistAliasList(List<ArtistAliasList> artistAliasList) {
        this.artistAliasList = artistAliasList;
    }

    public Integer getArtistRating() {
        return artistRating;
    }

    public void setArtistRating(Integer artistRating) {
        this.artistRating = artistRating;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

}
