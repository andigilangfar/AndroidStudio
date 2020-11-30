package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tugas2.adapter.BeritaAdapter;
import com.example.tugas2.model.Berita;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
public class MainActivity extends AppCompatActivity {
    private static final int CODE_MAIN_ACTIVITY = 10 ;
    ListView beritaListView;
    Button newberitaButton;
    BeritaAdapter beritaAdapter;
    ArrayList<Berita> listBerita= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();

    }
    void findViewById(){
        beritaListView = (ListView) findViewById(R.id.beritaListView);
        newberitaButton = (Button) findViewById(R.id.newberitaButton);
    }
    void onClickGroup(){
        newberitaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id", "CEK");
                startActivityForResult(intent, CODE_MAIN_ACTIVITY);
            }
        });
    }

    private void parse( String data){
        Gson gson = new Gson();
        Berita model = gson.fromJson(data,Berita.class);
        Log.v("IsiModel: ",model.getTitle());
        listBerita.add(model);
        beritaAdapter.notifyDataSetChanged();
    }

    void initData() {
        beritaListView = (ListView) findViewById(R.id.beritaListView);
        newberitaButton = (Button) findViewById(R.id.newberitaButton);
        beritaAdapter = new BeritaAdapter(getApplicationContext(),listBerita);
        beritaListView.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }
}