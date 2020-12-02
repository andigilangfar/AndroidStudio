package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tugas4.adapter.PulsaAdapter;
import com.example.tugas4.models.Pulsa;
import com.example.tugas4.viewmodel.PulsaViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView pulsaRecyclerView;
    private ArrayList<Pulsa> arrTemp =new ArrayList<>();
    private PulsaAdapter pulsaAdapter;
    private PulsaViewModel pulsaViewModel;
    private List<Pulsa> pulsaList;
    private RelativeLayout layout_rincian;
    private AppCompatImageView closeIV;
    private AppCompatTextView paymentTV,pulsaTV;
    private CoordinatorLayout layoutPulsa;
    private LinearLayoutCompat paymentBT;
    private View viewfocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView(){
        pulsaRecyclerView=findViewById(R.id.pulsaRecyclerView);
        layout_rincian=findViewById(R.id.layout_rincian);
        closeIV=findViewById(R.id.closeIV);
        paymentTV=findViewById(R.id.paymentTV);
        pulsaTV=findViewById(R.id.pulsaTV);
        layoutPulsa=findViewById(R.id.layoutPulsa);
        paymentBT=findViewById(R.id.paymentBT);
    }

    private void initData(){
        pulsaViewModel = ViewModelProviders.of(this).get(PulsaViewModel.class);
        if (pulsaAdapter==null){
            pulsaAdapter=new PulsaAdapter(MainActivity.this,arrTemp,layout_rincian,viewfocus,
                    closeIV,paymentTV,layoutPulsa,pulsaTV,paymentBT,pulsaViewModel);
            pulsaRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
            pulsaRecyclerView.setAdapter(pulsaAdapter);
            pulsaRecyclerView.setHasFixedSize(true);
            pulsaRecyclerView.setItemAnimator(new DefaultItemAnimator());
            pulsaRecyclerView.setNestedScrollingEnabled(true);
        } else {
            pulsaAdapter.notifyDataSetChanged();
        }
        pulsaViewModel.init();
        pulsaViewModel.getPulsa().observe(this,pulsaResponse -> {
            pulsaList=pulsaResponse.getData();
            arrTemp.clear();
            arrTemp.addAll(pulsaList);
            pulsaAdapter.notifyDataSetChanged();
        });
    }
}