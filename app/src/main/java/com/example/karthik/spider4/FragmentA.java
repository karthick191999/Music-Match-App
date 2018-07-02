package com.example.karthik.spider4;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.karthik.spider4.MusicGenre.GenreListResponse;
import com.example.karthik.spider4.TopTracks.TrackListResponse;
import com.example.karthik.spider4.TopTracks.Tracklist;

import java.util.ArrayList;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by karthik on 28-06-2018.
 */

public class FragmentA extends android.support.v4.app.Fragment {
    String BASE_URL = "http://api.musixmatch.com/ws/1.1/";
    String API_KEY = "28ba11b5b9e792712fa5b640c26d6398";
    ListView listView;
    DatabaseHelper databaseHelper;
    boolean check[] = new boolean[15];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        listView = (ListView) view.findViewById(R.id.track_fragment);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        databaseHelper = new DatabaseHelper(getActivity());
        APIinterface apIinterface = retrofit.create(APIinterface.class);
        Call<TrackListResponse> call = apIinterface.getTopTracks(API_KEY, 1, 15, "in");
        call.enqueue(new Callback<TrackListResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<TrackListResponse> call, Response<TrackListResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<Tracklist> track = (ArrayList<Tracklist>) response.body().getMessage().getBody().getTrackList();
                    topTrack_Adapter adapter = new topTrack_Adapter(getActivity(), R.layout.toptrack_singlerow, track);
                    listView.setAdapter(adapter);
                }
            }


            @Override
            public void onFailure(Call<TrackListResponse> call, Throwable t) {
                Log.d("NOt working ", t.getMessage());
            }
        });
        for (int i = 0; i < 15; i++) {
            check[i] = false;
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Checking log", "Inside on click");
                Intent intent = new Intent(getActivity(), TrackDetails.class);
                Bundle bundle = new Bundle();
                Tracklist tracklist = (Tracklist) listView.getItemAtPosition(position);
                int albumId = tracklist.getTrack().getAlbumId();
                bundle.putInt("TrackId", tracklist.getTrack().getTrackId());
                bundle.putInt("Id", albumId);
                bundle.putInt("Position", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        return view;
    }

    class topTrack_Adapter extends ArrayAdapter<Tracklist> {

        public topTrack_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Tracklist> objects) {
            super(context, resource, objects);

        }

        private boolean[] favorites = new boolean[15];

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final int[] flag = {0};
            final String[] yearRelease = new String[1];
            View row = convertView;
            final Tracklist tracklist = getItem(position);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIinterface apIinterface = retrofit.create(APIinterface.class);

            int albumId = tracklist.getTrack().getAlbumId();
            Call<GenreListResponse> call = apIinterface.getTrackdetails(API_KEY, albumId);
            call.enqueue(new Callback<GenreListResponse>() {
                @Override
                public void onResponse(Call<GenreListResponse> call, Response<GenreListResponse> response) {
                    GenreListResponse genreListResponse = response.body();
                    yearRelease[0] = genreListResponse.getMessage().getBody().getAlbum().getAlbumReleaseDate();

                }


                @Override
                public void onFailure(Call<GenreListResponse> call, Throwable t) {

                }
            });
            if (row == null) {
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                row = layoutInflater.inflate(R.layout.toptrack_singlerow, parent, false);
            }


            final ImageView imageView = (ImageView) row.findViewById(R.id.favouriteT);
            if (favorites[position]) {
                imageView.setImageResource(R.drawable.favourite_yes);
            } else {
                imageView.setImageResource(R.drawable.favourite_no);
            }


            String pos = getposition();
            //  Log.d("SHARED", pos);
            StringTokenizer savePosition = new StringTokenizer(pos, ",");
            for (int i = 0; i < pos.length(); i++) {
                while (savePosition.hasMoreTokens()) {
                    if (position == Integer.parseInt(savePosition.nextToken())) {
                      imageView.setImageResource(R.drawable.favourite_yes);
                        favorites[position]=true;
                    }
                }
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (favorites[position]) {
                        imageView.setImageResource(R.drawable.favourite_no);
                        favorites[position] = false;
                    } else {
                        imageView.setImageResource(R.drawable.favourite_yes);

                        favorites[position] = true;
                    }

                    String pos = getposition();

                    StringTokenizer savePosition = new StringTokenizer(pos, ",");
                    for (int i = 0; i < pos.length(); i++) {
                        while (savePosition.hasMoreTokens()) {

                            if (position == Integer.parseInt(savePosition.nextToken())) {
                                flag[0] = 1;
                                break;
                            }
                        }
                    }


                    if (flag[0] == 1) {
                        Toast.makeText(getActivity(), "ALreaady exist", Toast.LENGTH_LONG).show();
                        //  AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        //dialog.setMessage("Already exist");
                        //dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        //@Override
                        //  public void onClick(DialogInterface dialog, int which) {store(position,1);
                        //  Log.d("Checking name", tracklist.getTrack().getTrackName());
                        //  databaseHelper.deleteData(tracklist.getTrack().getTrackName());
                        //int del = position;
                        // }
                        //});
                        //dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        //@Override
                        //  public void onClick(DialogInterface dialog, int which) {

                        //        }
                        //});
                        //  dialog.show();

                    } else {
                        store(position);
                        Log.d("Checking Position", String.valueOf(position));
                        databaseHelper.addTrackData(tracklist.getTrack().getTrackName(), tracklist.getTrack().getArtistName(), yearRelease[0]);

                    }
                }
            });
            TextView trackname = (TextView) row.findViewById(R.id.idividual_track);
            trackname.setText(tracklist.getTrack().getTrackName());
            return row;
        }
    }

    public void store(int position) {
        StringBuilder str = new StringBuilder();


        str.append(getposition());
        //    if (flag == 1) {
        // String string = str.toString();
        //   char a[] = string.toCharArray();
        //     for (int i = 0; i < a.length; i++)
        //if (a[i] == position || a[i] == ',')
        //      a[i] = 'e';
        //string = String.valueOf(a);
        //Log.d("Checking string",string);
        //  str.append(string);
        //    Log.d("Checking yra", String.valueOf(str));

        //  }
        str.append(position + ",");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Save Track", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PositionTrack", String.valueOf(str));
        editor.commit();
    }


    public String getposition() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Save Track", Context.MODE_PRIVATE);
        return sharedPreferences.getString("PositionTrack", "");
    }
}



