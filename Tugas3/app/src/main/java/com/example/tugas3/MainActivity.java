package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.berita_restapi.adapter.BeritaAdapter;
import com.example.tugas3.model.Berita;
import com.example.tugas3.viewmodels.BeritaViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Berita> beritaArr = new ArrayList<>();
    com.example.berita_restapi.adapter.BeritaAdapter beritaAdapter;
    RecyclerView beritaRecyclerView;
    BeritaViewModel beritaViewModel;
    TextView refreshButton, addButton;
    List<Berita> beritaList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initData();
        onClickGroup();
    }

    void findViewById(){
        beritaRecyclerView = findViewById(R.id.beritaRecyclerView);
        refreshButton = findViewById(R.id.refreshButton);
        addButton = findViewById(R.id.addButton);
    }

    private void initData(){
        if ( beritaAdapter== null) {
            beritaAdapter = new BeritaAdapter(MainActivity.this, beritaArr);
            beritaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            beritaRecyclerView.setAdapter(beritaAdapter);
            beritaRecyclerView.setItemAnimator(new DefaultItemAnimator());
            beritaRecyclerView.setNestedScrollingEnabled(true);
        } else {
            beritaAdapter.notifyDataSetChanged();
        }
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);

        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);
        beritaViewModel.init();
        beritaViewModel.getBeritaRepository().observe(this, newsResponse -> {
            List<Berita> newNews =newsResponse.getData();
            beritaArr.addAll(newNews);
            beritaAdapter.notifyDataSetChanged();
        });
    }

    public void getListBerita(String page, String limit){
        beritaViewModel.refresh(page, limit);
        beritaViewModel.getBeritaRepository().observe(this, newsResponse -> {
            beritaList = newsResponse.getData();
            beritaArr.clear();
            beritaArr.addAll(beritaList);
            beritaAdapter.notifyDataSetChanged();
        });
    }

    void onClickGroup(){
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListBerita("1", "20");
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getListBerita("1","20");
    }
}