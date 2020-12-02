package com.example.tugas4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugas4.models.Pulsa;
import com.example.tugas4.models.PulsaResponse;
import com.example.tugas4.networking.PulsaRepositories;

public class PulsaViewModel extends ViewModel {
    private MutableLiveData<PulsaResponse> mutableLiveData;
    private PulsaRepositories pulsaRepositories;

    public void init(){
        if (mutableLiveData!=null){
            return;
        }
        pulsaRepositories = PulsaRepositories.getInstance();
        mutableLiveData=pulsaRepositories.getPulsa();
    }

    public LiveData<PulsaResponse> getPulsa(){
        return mutableLiveData;
    }

    public LiveData<PulsaResponse> postPulsa(Pulsa pulsa){
        if (mutableLiveData==null){
            pulsaRepositories =PulsaRepositories.getInstance();
        }
        mutableLiveData=pulsaRepositories.postPulsa(pulsa);
        return mutableLiveData;
    }
}