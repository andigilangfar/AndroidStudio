package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewActivity extends AppCompatActivity {

    private AppCompatButton homeBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
        onClick();
    }

    private void initView(){
        homeBT=findViewById(R.id.homeBT);
    }

    private void onClick(){
        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}