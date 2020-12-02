package com.example.tugas4.networking;

import com.example.tugas4.models.Pulsa;
import com.example.tugas4.models.PulsaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PulsaApi {
    @GET("product")
    Call<PulsaResponse> getPulsa();

    @POST("pulsa")
    Call<PulsaResponse> buyPulsa(@Body Pulsa pulsa);
}
