package com.example.karthik.spider4;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;

/**
 * Created by karthik on 01-07-2018.
 */

public class FragmentC extends Fragment {
    DatabaseHelper databaseHelper;

    ListView listView;
    ArrayList<TrackD> favTracks = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        listView = (ListView) view.findViewById(R.id.favTracks);
        Log.d("Checking fragment","Fragmentc yeah");
        databaseHelper = new DatabaseHelper(getActivity());
        Cursor cursor = databaseHelper.getFavTrackData();
        favTracks.clear();
        while (cursor.moveToNext()) {
            favTracks.add(new TrackD(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        favTrackAdapter adapter = new favTrackAdapter(getActivity(),R.layout.fav_single_track,favTracks);
        listView.setAdapter(adapter);
    return view;
    }


    class favTrackAdapter extends ArrayAdapter<TrackD> {
        public favTrackAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<TrackD> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;


            TrackD trackD = getItem(position);
            if (row == null) {
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
              row=  layoutInflater.inflate(R.layout.fav_single_track, parent, false);
            }

            TextView trackname, artist, year;

            trackname = (TextView) row.findViewById(R.id.fav_track_name);
            artist = (TextView) row.findViewById(R.id.fav_track_artist);
            year = (TextView) row.findViewById(R.id.fav_track_year);
            trackname.setText(trackD.getName());
            artist.setText(trackD.getArtist());
            year.setText(trackD.getYear());

        return row;
        }
    }


}
