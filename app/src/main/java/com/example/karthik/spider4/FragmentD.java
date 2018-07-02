package com.example.karthik.spider4;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthik on 02-07-2018.
 */

public class FragmentD extends Fragment {
    DatabaseArtist databaseArtist;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);
        listView = (ListView) view.findViewById(R.id.favartists);
        databaseArtist = new DatabaseArtist(getActivity());
        ArrayList<ArtistD> list = new ArrayList<>();
        Cursor artistCursor = databaseArtist.getFavArtistData();
        while (artistCursor.moveToNext()) {
            list.add(new ArtistD(artistCursor.getString(1), artistCursor.getString(2), artistCursor.getString(3)));
        }
        favArtistAdapter adapter = new favArtistAdapter(getActivity(), R.layout.fav_single_artist, list);
        listView.setAdapter(adapter);
        return view;
    }


    class favArtistAdapter extends ArrayAdapter<ArtistD> {
        public favArtistAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ArtistD> objects) {
            super(context, resource, objects);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ArtistD artist = getItem(position);
            if (row == null) {
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                row = layoutInflater.inflate(R.layout.fav_single_artist, parent, false);
            }
            TextView aname, arating, agenre;
            aname = (TextView) row.findViewById(R.id.favartName);
            arating = (TextView) row.findViewById(R.id.favartRating);
            agenre = (TextView) row.findViewById(R.id.favartGenre);
            aname.setText(artist.getArtistName());
            arating.setText(artist.getArtistRating());
            agenre.setText(artist.getArtistGenre());

            return row;
        }
    }


}
