package com.example.broadway.view;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.broadway.controller.MainController;
import com.example.broadway.R;
import com.example.broadway.model.Musical;
import com.example.broadway.model.OnItemClickListener;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar loader;
    private Random rand = new Random();

    private MainController controller;

    MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        loader = findViewById(R.id.loader_main_activity);

        controller = new MainController(this, getSharedPreferences("listMusical", Context.MODE_PRIVATE));
        controller.onCreate();

        mySong = MediaPlayer.create(MainActivity.this, R.raw.here_we_go_again);
    }

    //Audio and Switch on action bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final MediaPlayer[] mySongs = {
                MediaPlayer.create(getApplicationContext(), R.raw.here_we_go_again),
                MediaPlayer.create(getApplicationContext(), R.raw.do_you_hear_the_people_sing),
                MediaPlayer.create(getApplicationContext(), R.raw.the_lion_king),
                MediaPlayer.create(getApplicationContext(), R.raw.alexander_hamilton),
        };
        getMenuInflater().inflate(R.menu.action_menu, menu);

        MenuItem itemSwitch = menu.findItem(R.id.mySwitch);
        itemSwitch.setActionView(R.layout.use_switch);
        final Switch sw = (Switch) menu.findItem(R.id.mySwitch).getActionView().findViewById(R.id.action_switch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Sing It Loud!!", Toast.LENGTH_SHORT).show();
                    mySongs[rand.nextInt(mySongs.length)].start();
                }
            }
        });
        return true;
    }
    @Override
    protected void onPause() {
        super.onPause();
        mySong.release();
    }

    //Animation entre les activitées
    public void openDetailActivity(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void showList(List<Musical> list){
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new MyAdapter(MainActivity.this, list, new OnItemClickListener() {
            @Override
            public void onItemClick(Musical item) {
                Toast.makeText(getApplicationContext(),item.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("theatre", item.getTheatre());
                intent.putExtra("story", item.getStory());
                intent.putExtra("card", item.getImagecard_url());
                intent.putExtra("director", item.getDirector());


                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }
}
