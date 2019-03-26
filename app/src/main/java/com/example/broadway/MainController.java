package com.example.broadway;

import com.example.broadway.model.Musical;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.LinearLayout;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private final MainActivity mainActivity;
    private static String key = "key";
    private SharedPreferences sharedPreferences;


    private static MainController instance = null;

    //Exemple Singleton
    public static MainController getInstance(MainActivity mainActivity, SharedPreferences sharedPreferences){
        if(instance == null){
            instance = new MainController(mainActivity, sharedPreferences);
        }
        return instance;

    }
    public MainController(MainActivity mainActivity, SharedPreferences sharedPreferences) {
        this.mainActivity = mainActivity;
        this.sharedPreferences = sharedPreferences;
    }

    public void onCreate() {

        if (sharedPreferences.contains(key)){
            String cache = sharedPreferences.getString(key, null);
            Type listType = new TypeToken<List<Musical>>(){}.getType();
            List<Musical> listMusical = new Gson().fromJson(cache, listType);
            mainActivity.showList(listMusical);

        } else{
            //On crée un objet Gson
            final Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //On crée un objet retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/yohannaTML/musical-server/master/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            //On crée notre interface MusicalRestApi
            MusicalRestApi musicalRestApi = retrofit.create(MusicalRestApi.class);

            //On récupére un objet call.
            Call<List<Musical>> call = musicalRestApi.Musical();

            call.enqueue(new Callback<List<Musical>>() {
                @Override
                public void onResponse(Call<List<Musical>> call, Response<List<Musical>> response) {
                    List<Musical> listMusical = response.body();

                    Gson gson1 = new Gson();
                    String cache = gson1.toJson(listMusical);

                    sharedPreferences.edit().putString(key, cache).apply();
                    mainActivity.showList(listMusical);
                }

                @Override
                public void onFailure(Call<List<Musical>> call, Throwable t) {
                    Log.d("Erreur", "API ERROR");
                }
            });
        }

    }

}
