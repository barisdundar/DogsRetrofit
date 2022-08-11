package com.example.proje.GoruntulemeEkranıp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoruntulenmeRetrofitClient {

    private static Retrofit retrofit;

    private static String BASE_URL = "https://dog.ceo/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
