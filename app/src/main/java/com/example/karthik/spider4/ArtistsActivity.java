package com.example.karthik.spider4;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karthik.spider4.TopArtists.ArtistList;
import com.example.karthik.spider4.TopArtists.ArtistListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistsActivity extends AppCompatActivity {
    String BASE_URL = "http://api.musixmatch.com/ws/1.1/";
    String API_KEY = "28ba11b5b9e792712fa5b640c26d6398";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);
        Toast.makeText(getApplicationContext(), "We are inside artists activity", Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIinterface apIinterface = retrofit.create(APIinterface.class);
        listView = (ListView) findViewById(R.id.topArtistList);
        Call<ArtistListResponse> artistListResponseCall = apIinterface.getTopArtists(API_KEY, 1, 15, "in");
        artistListResponseCall.enqueue(new Callback<ArtistListResponse>() {
            @Override
            public void onResponse(Call<ArtistListResponse> call, Response<ArtistListResponse> response) {
                ArtistListResponse artistListResponse = response.body();
                List<ArtistList> artistLists = artistListResponse.getMessage().getBody().getArtistList();
                customArtists adapter = new customArtists(ArtistsActivity.this, R.layout.topartist_singlerow, artistLists);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArtistListResponse> call, Throwable t) {

            }
        });


    }

    class customArtists extends ArrayAdapter<ArtistList> {

        public customArtists(@NonNull Context context, @LayoutRes int resource, @NonNull List<ArtistList> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ArtistList artistList = getItem(position);
            if (row == null) {
                LayoutInflater layoutInflater = getLayoutInflater();
                row = layoutInflater.inflate(R.layout.topartist_singlerow, parent, false);
            }
            TextView artisttext = (TextView) row.findViewById(R.id.individual_artist);
            String artistName = artistList.getArtist().getArtistName();
            artisttext.setText(artistName);
            return row;
        }
    }


}
