package com.example.tugas3.networking;

import com.example.tugas3.model.Berita;
import com.example.tugas3.model.BeritaResponse;
import com.example.tugas3.model.SingleBeritaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BeritaApi {
    @GET("berita")
    Call<BeritaResponse> getBeritaList(@Query("page")String page,
                                       @Query("limit")String limit);
    @POST("berita")
    Call<SingleBeritaResponse> postBerita (@Body Berita body);
}
