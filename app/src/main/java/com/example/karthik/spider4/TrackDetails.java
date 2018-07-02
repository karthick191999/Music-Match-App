package com.example.karthik.spider4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.karthik.spider4.MusicGenre.GenreListResponse;
import com.example.karthik.spider4.MusicGenre.MusicGenreList;
import com.example.karthik.spider4.TopTracks.PrimaryGenres;
import com.example.karthik.spider4.TopTracks.TrackListResponse;
import com.example.karthik.spider4.TopTracks.Tracklist;
import com.example.karthik.spider4.TracksGet.TrackResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackDetails extends AppCompatActivity {
    String BASE_URL = "http://api.musixmatch.com/ws/1.1/";
    String API_KEY = "28ba11b5b9e792712fa5b640c26d6398";
    TextView title, artist, album, genretrack, TyearRelease;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);
        title = (TextView) findViewById(R.id.trackTitle);
        artist = (TextView) findViewById(R.id.trackArtist);
        album = (TextView) findViewById(R.id.trackAlbum);
        genretrack = (TextView) findViewById(R.id.trackGenre);
        TyearRelease = (TextView) findViewById(R.id.yrRelease);
        Bundle bundle = getIntent().getExtras();
        int albumId = bundle.getInt("Id");
        int trackId = bundle.getInt("TrackId");
        Log.d("Checking track id", String.valueOf(trackId));
        final int position = bundle.getInt("Position");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIinterface apIinterface = retrofit.create(APIinterface.class);

        Call<TrackResponse> calltrack = apIinterface.getTracknotSearch(API_KEY, trackId);
        calltrack.enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(Call<TrackResponse> calltrack, Response<TrackResponse> response) {
                String genre = "";
                TrackResponse trackResponse = response.body();
                String name = trackResponse.getMessage().getBody().getTrack().getTrackName();
                PrimaryGenres primaryGenres = trackResponse.getMessage().getBody().getTrack().getPrimaryGenres();

                List<com.example.karthik.spider4.TopTracks.MusicGenreList> lists = primaryGenres.getMusicGenreList();
                for (int i = 0; i < lists.size(); i++) {
                    genre = lists.get(i).getMusicGenre().getMusicGenreName();
                }
                //String name = trackListResponse.getMessage().getBody().getTracksGet().getTrack().getTrackName();
                Log.d("Checking parsing", name);
                title.setText(name);
                genretrack.setText(genre);

                //Log.d("CHECKING GENRE",genre);
            }


            @Override
            public void onFailure(Call<TrackResponse> calltrack, Throwable t) {

            }
        });


        Call<GenreListResponse> call = apIinterface.getTrackdetails(API_KEY, albumId);
        call.enqueue(new Callback<GenreListResponse>() {
            @Override
            public void onResponse(Call<GenreListResponse> call, Response<GenreListResponse> response) {
                GenreListResponse genreListResponse = response.body();
                String albumname = genreListResponse.getMessage().getBody().getAlbum().getAlbumName();
                String artistname = genreListResponse.getMessage().getBody().getAlbum().getArtistName();
                String yearRelease = genreListResponse.getMessage().getBody().getAlbum().getAlbumReleaseDate();
                album.setText(albumname);
                artist.setText(artistname);
                TyearRelease.setText(yearRelease);

            }

            @Override
            public void onFailure(Call<GenreListResponse> call, Throwable t) {

            }
        });


    }
}
