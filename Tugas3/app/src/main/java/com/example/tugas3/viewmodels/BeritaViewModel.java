package com.example.tugas3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugas3.model.Berita;
import com.example.tugas3.model.BeritaResponse;
import com.example.tugas3.model.SingleBeritaResponse;
import com.example.tugas3.networking.BeritaRepository;

public class BeritaViewModel extends ViewModel {
    private MutableLiveData<BeritaResponse> mutableLiveData;
    private BeritaRepository beritaRepository;
    private MutableLiveData<SingleBeritaResponse> mutableSingleData;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        beritaRepository = BeritaRepository.getInstance();
        mutableLiveData = beritaRepository.getAllBerita("1", "10");
    }

    public LiveData<BeritaResponse> getBeritaRepository() {
        return mutableLiveData;
    }

    public void refresh(String page, String limit) {
        if (mutableLiveData != null) {
            mutableLiveData = beritaRepository.getAllBerita(page, limit);
            return;
        }
        beritaRepository = BeritaRepository.getInstance();
        mutableLiveData = beritaRepository.getAllBerita("1", "10");
    }

    public LiveData<SingleBeritaResponse> postBeritaRepository(Berita payload) {
        if (mutableSingleData == null) {
            beritaRepository = beritaRepository.getInstance();
        }
        mutableSingleData = beritaRepository.postBerita(payload);
        return mutableSingleData;
    }

}
