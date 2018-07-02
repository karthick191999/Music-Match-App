package com.example.karthik.spider4.TracksGet;

import com.example.karthik.spider4.TopTracks.Track;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karthik on 29-06-2018.
 */

public class Body {


    @SerializedName("track")
    @Expose
    private Track track;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

}
