package com.catatanasad.crudmakanan.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.60.148:8888/server_resto_ios/index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService service = retrofit.create(ApiService.class);

}