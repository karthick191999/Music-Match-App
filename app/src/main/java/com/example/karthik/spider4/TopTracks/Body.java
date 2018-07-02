package com.example.karthik.spider4.TopTracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by karthik on 25-06-2018.
 */
public class Body {

    @SerializedName("track_list")
    @Expose
    private List<Tracklist> trackList = null;




    public List<Tracklist> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Tracklist> trackList) {
        this.trackList = trackList;
    }
}