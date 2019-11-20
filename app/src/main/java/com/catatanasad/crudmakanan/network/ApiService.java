package com.catatanasad.crudmakanan.network;

import com.catatanasad.crudmakanan.model.ResponseGetMakanan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("Api/getMakanan")
    Call<ResponseGetMakanan> getMakanan();

    @FormUrlEncoded
    @POST("Api/insertMakanan")
    Call<ResponseGetMakanan> insertMakanan(@Field("name") String nama,
                                           @Field("price") String harga,
                                           @Field("gambar") String urlgambar);


    @FormUrlEncoded
    @POST("Api/updateMakanan")
    Call<ResponseGetMakanan> updateMakanan(@Field("id") String id,
                                           @Field("name") String nama,
                                           @Field("price") String harga,
                                           @Field("gambar") String urlgambar);

    @FormUrlEncoded
    @POST("Api/deleteMakanan")
    Call<ResponseGetMakanan> deleteMakanan(@Field("id") String id);
}
