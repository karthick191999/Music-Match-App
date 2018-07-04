package com.example.karthik.spider4;

import com.example.karthik.spider4.ArtistGet.Artistget;
import com.example.karthik.spider4.MusicGenre.GenreListResponse;
import com.example.karthik.spider4.TopArtists.ArtistListResponse;
import com.example.karthik.spider4.TopTracks.TrackListResponse;
import com.example.karthik.spider4.TracksGet.TrackResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by karthik on 23-06-2018.
 */

public interface APIinterface {
    @GET("chart.tracks.get")
    Call<TrackListResponse> getTopTracks(@Query("apikey") String apiKey, @Query("page") int page, @Query("page_size") int page_size, @Query("country") String country);

    @GET("chart.artists.get")
    Call<ArtistListResponse> getTopArtists(@Query("apikey") String apiKey, @Query("page") int page, @Query("page_size") int page_size, @Query("country") String country);

    @GET("track.get")
    Call<TrackResponse> getTracknotSearch(@Query("apikey") String apiKey, @Query("track_id") int trackId);


    @GET("album.get")
    Call<GenreListResponse> getTrackdetails(@Query("apikey") String apiKey, @Query("album_id") int albumId);

//    @GET("track.search")
  //  Call<TrackListResponse> getTrackbyTrack(@Query("apikey") String apiKey, @Query("q_track") String name, @Query("page_size") int page_size, @Query("page") int page, @Query("s_track_rating") String track_rating);

    @GET("track.search")
    Call<TrackListResponse> getTrack(@Query("apikey") String apiKey, @Query("q_track") String name, @Query("page_size") int page_size, @Query("page") int page, @Query("s_track_rating") String track_rating);

    @GET("track.search")
    Call<TrackListResponse> getSearchArtistr(@Query("apikey") String apiKey, @Query("q_artist") String name, @Query("page_size") int page_size, @Query("page") int page, @Query("s_track_rating") String track_rating);

    @GET("track.search")
    Call<TrackListResponse> getSearchLyric(@Query("apikey") String apiKey, @Query("q_lyrics") String name, @Query("page_size") int page_size, @Query("page") int page, @Query("s_track_rating") String track_rating);



    @GET("artist.get")
    Call<Artistget> getArtist(@Query("apikey") String apiKey, @Query("artist_id") int artistid);

}
