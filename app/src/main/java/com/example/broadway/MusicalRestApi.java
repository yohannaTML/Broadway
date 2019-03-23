package com.example.broadway;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MusicalRestApi {

    @GET("db.json")
    Call<List> Musical();
}
