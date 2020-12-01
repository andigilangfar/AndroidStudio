package com.example.tugas3.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tugas3.model.Berita;
import com.example.tugas3.model.BeritaResponse;
import com.example.tugas3.model.SingleBeritaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaRepository {
    public static BeritaRepository beritaRepository;

    public static BeritaRepository getInstance(){
        if (beritaRepository == null){
            beritaRepository = new BeritaRepository();
        }
        return beritaRepository;
    }

    private BeritaApi beritaApi;
    public BeritaRepository(){
        beritaApi = RetrofitService.createService(BeritaApi.class);
    }

    public MutableLiveData<BeritaResponse> getAllBerita(String page, String limit){
        MutableLiveData<BeritaResponse> beritaData = new MutableLiveData<>();
        beritaApi.getBeritaList(page, limit).enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call,
                                   Response<BeritaResponse> response) {
                if (response.isSuccessful()){
                    Log.d("SUCCESS ",response.body().toString());
                    beritaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                beritaData.setValue(null);
            }
        });
        return beritaData;
    }
    public MutableLiveData<SingleBeritaResponse> postBerita(Berita nasabahPayload){
        MutableLiveData<SingleBeritaResponse> beritaData = new MutableLiveData<>();
        beritaApi.postBerita(nasabahPayload).enqueue(new Callback<SingleBeritaResponse>() {
            @Override
            public void onResponse(Call<SingleBeritaResponse> call,
                                   Response<SingleBeritaResponse> response) {
                if (response.isSuccessful()){
                    beritaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SingleBeritaResponse> call, Throwable t) {
                beritaData.setValue(null);
            }
        });
        return beritaData;
    }
}
