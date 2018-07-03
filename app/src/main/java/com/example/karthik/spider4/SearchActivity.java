package com.example.karthik.spider4;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.karthik.spider4.TopTracks.Track;
import com.example.karthik.spider4.TopTracks.TrackListResponse;
import com.example.karthik.spider4.TopTracks.Tracklist;
import com.example.karthik.spider4.TracksGet.TrackResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    String BASE_URL = "http://api.musixmatch.com/ws/1.1/";
    String API_KEY = "28ba11b5b9e792712fa5b640c26d6398";
    ListView searchList;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Searchtext");
        textView = (TextView) findViewById(R.id.searchtext);
        searchList = (ListView) findViewById(R.id.searchList);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIinterface apIinterface = retrofit.create(APIinterface.class);

        // Not working apparently :(
    /*    if (!bundle.getBoolean("Check")) {   Call<TrackListResponse> search = apIinterface.getTrackbyTrack(API_KEY, name,13,1,"desc");
        search.enqueue(new Callback<TrackListResponse>() {
            @Override
            public void onResponse(Call<TrackListResponse> call, Response<TrackListResponse> response) {
                List<Tracklist> list = response.body().getMessage().getBody().getTrackList();
                for (int i=0;i<list.size();i++){
                    Log.d("HAHAHAH",list.get(i).getTrack().getTrackName());
                }
            }

            @Override
            public void onFailure(Call<TrackListResponse> call, Throwable t) {

            }
        });
        }*/
        //if (bundle.getBoolean("Check")) {
            Call<TrackListResponse> call = apIinterface.getTrack(API_KEY, name, 30, 1, "desc");
            call.enqueue(new Callback<TrackListResponse>() {
                @Override
                public void onResponse(Call<TrackListResponse> call, Response<TrackListResponse> response) {
                    TrackListResponse trackListResponse = response.body();

                    List<Tracklist> trackLists = trackListResponse.getMessage().getBody().getTrackList();
                    if (String.valueOf(trackLists)=="[]")
                        Toast.makeText(SearchActivity.this,"No such element:(",Toast.LENGTH_LONG).show();
                    else
                    {  searchAdapter adapter = new searchAdapter(SearchActivity.this, R.layout.search_single_row, trackLists);
                    searchList.setAdapter(adapter);}
                }

                @Override
                public void onFailure(Call<TrackListResponse> call, Throwable t) {
                    Toast.makeText(SearchActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                }
            });

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent searchIntent = new Intent(SearchActivity.this,TrackDetails.class);
                Bundle bundle = new Bundle();
               Tracklist tracklist  = (Tracklist) searchList.getItemAtPosition(position);
                int albumId = tracklist.getTrack().getAlbumId();
                bundle.putInt("TrackId", tracklist.getTrack().getTrackId());
                bundle.putInt("Id", albumId);
                bundle.putInt("Position", position);
                searchIntent  .putExtras(bundle);
                startActivity(searchIntent);
            }
        });




        }
    //}

    class searchAdapter extends ArrayAdapter<Tracklist> {

        public searchAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Tracklist> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;

            Tracklist trackList = getItem(position);
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.search_single_row, parent, false);
            }
            TextView searchResult = (TextView) row.findViewById(R.id.searchedText);
            searchResult.setText(trackList.getTrack().getTrackName());
            return row;
        }
    }


}
