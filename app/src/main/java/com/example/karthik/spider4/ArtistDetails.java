package com.example.karthik.spider4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.karthik.spider4.ArtistGet.Artistget;
import com.example.karthik.spider4.MusicGenre.MusicGenre;
import com.example.karthik.spider4.TopTracks.MusicGenreList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistDetails extends AppCompatActivity {
    String BASE_URL = "http://api.musixmatch.com/ws/1.1/";
    String API_KEY = "28ba11b5b9e792712fa5b640c26d6398";
    TextView aname, arating, agenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_details);

        aname = (TextView) findViewById(R.id.artistName);
        arating = (TextView) findViewById(R.id.artistRating);
        agenre = (TextView) findViewById(R.id.artistGenre);
        Bundle bundle = getIntent().getExtras();
        int artistId = bundle.getInt("artistId");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIinterface apIinterface = retrofit.create(APIinterface.class);
        Call<Artistget> call = apIinterface.getArtist(API_KEY, artistId);
        call.enqueue(new Callback<Artistget>() {
            @Override
            public void onResponse(Call<Artistget> call, Response<Artistget> response) {
                StringBuilder genre = new StringBuilder();
                String name = response.body().getMessage().getBody().getArtist().getArtistName();
                int rating = response.body().getMessage().getBody().getArtist().getArtistRating();
                aname.setText(name);
                arating.setText(String.valueOf(rating));
                boolean primaryGenre = true;
                if (response.body().getMessage().getBody().getArtist().getPrimaryGenres() == null) {
                    primaryGenre = false;
                }
                List<MusicGenreList> list = null;
                if (primaryGenre) {

                    list = response.body().getMessage().getBody().getArtist().getPrimaryGenres().getMusicGenreList();
                    for (int i = 0; i < list.size(); i++) {
                        if (i == list.size() - 1)
                            genre.append(list.get(i).getMusicGenre().getMusicGenreName());
                        else
                            genre.append(list.get(i).getMusicGenre().getMusicGenreName() + ",");

                    }
                }
                if (primaryGenre==false||list.size() <= 0) {
                    agenre.setText("Genre Unavailable");
                } else
                    agenre.setText(genre);
            }

            @Override
            public void onFailure(Call<Artistget> call, Throwable t) {

            }
        });


    }
}
