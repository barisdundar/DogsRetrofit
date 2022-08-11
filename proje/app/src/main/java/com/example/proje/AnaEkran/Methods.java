package com.example.proje.AnaEkran;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Methods {

    @GET("api/breeds/list/all")
    Call<Model>getAllData();
}
