package com.example.karthik.spider4.MusicGenre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by karthik on 26-06-2018.
 */

public class SecondaryGenres {

    @SerializedName("music_genre_list")
    @Expose
    private List<Object> musicGenreList = null;

    public List<Object> getMusicGenreList() {
        return musicGenreList;
    }

    public void setMusicGenreList(List<Object> musicGenreList) {
        this.musicGenreList = musicGenreList;
    }

}
