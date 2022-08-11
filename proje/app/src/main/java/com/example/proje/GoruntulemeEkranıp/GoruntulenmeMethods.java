package com.example.proje.GoruntulemeEkranıp;

import com.example.proje.AnaEkran.Model;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GoruntulenmeMethods {


    @GET
    Call<GoruntulenmeModel>getAllData(
            @Url String url
    );
}
