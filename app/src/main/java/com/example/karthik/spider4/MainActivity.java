package com.example.karthik.spider4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.karthik.spider4.TopArtists.ArtistList;
import com.example.karthik.spider4.TopArtists.ArtistListResponse;
import com.example.karthik.spider4.TopTracks.Track;
import com.example.karthik.spider4.TopTracks.TrackListResponse;
import com.example.karthik.spider4.TopTracks.Tracklist;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String BASE_URL = "http://api.musixmatch.com/ws/1.1/";
    String API_KEY = "28ba11b5b9e792712fa5b640c26d6398";
    ViewPager viewPager;
    private TabLayout tabLayout;
    EditText searchText;

    ImageButton imageButton;
    boolean trackBoolean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  ToggleButton toggleButton  = (ToggleButton) findViewById(R.id.toggle);
        //if (toggleButton.isChecked()){
        //trackBoolean   = true;
        //}
        //else
          //  trackBoolean = false;
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        searchText = (EditText) findViewById(R.id.search);
        pageAdapter adapter = new pageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = searchText.getText().toString();
                if (name.length()<=0) {
                    Toast.makeText(MainActivity.this, "Enter data", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    Bundle bundle = new Bundle();
                 //   bundle.putBoolean("Check",trackBoolean);
                    bundle.putString("Searchtext", name);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });


    }

    class pageAdapter extends FragmentPagerAdapter {


        public pageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new FragmentA();
            if (position == 1)
                return new FragmentB();
            if (position == 2)
                return new FragmentC();
            if (position == 3)
                return new FragmentD();

            return null;

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return "Tracks";
            if (position == 1)
                return "Artists";
            if (position == 2)
                return "Favourite Tracks";
            if (position == 3)
                return "Favourite Artist";
            return null;
        }
    }
}