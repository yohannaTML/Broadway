package com.example.broadway;

import com.example.broadway.model.Musical;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private final MainActivity mainActivity;

    private static MainController instance = null;

    //Exemple Singleton
    public static MainController getInstance(MainActivity mainActivity){
        if(instance == null){
            instance = new MainController(mainActivity);
        }
        return instance;

    }
    public MainController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void onCreate() {
        mainActivity.showLoader();



        //La création de ces objets, ce serait bien de ne pas
        // les réinstancier plusieurs fois.
        //--> Voir le cours de Génie Logiciel : Singleton()
        //Pour ceux qui veulent aller plus loin -> Injection de Dépendances
        //On crée un objet Gson
        Gson gson = new GsonBuilder()
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
        Call<List> call = musicalRestApi.Musical();

        call.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {
                List<Musical> listMusical = response.body();
                mainActivity.showList(listMusical);
                mainActivity.hideLoader();
            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {
                Log.d("Erreur", "API ERROR");
            }
        });
    }

    public void onItemClicked(Musical itemClicked){

    }

}
