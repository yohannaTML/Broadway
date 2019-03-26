package com.example.broadway.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.broadway.controller.MainController;
import com.example.broadway.R;
import com.example.broadway.model.Musical;
import com.example.broadway.model.OnItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar loader;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        loader = findViewById(R.id.loader_main_activity);

        controller = new MainController(this, getSharedPreferences("listMusical", Context.MODE_PRIVATE));
        controller.onCreate();
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
