package com.example.tugas3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas3.model.Berita;
import com.example.tugas3.viewmodels.BeritaViewModel;

public class ViewActivity extends AppCompatActivity {
    private String id, title, category, url;
    private Berita berita = new Berita();

    EditText titleEditText, categoryEditText, urlEditText;
    Button saveButton;
    String mode = "add";
    BeritaViewModel beritaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        findViewById();
        initData();
        onClickGroup();
    }

    void findViewById() {
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        categoryEditText = (EditText) findViewById(R.id.categoryEditText);
        urlEditText = (EditText) findViewById(R.id.urlEditText);
        saveButton = (Button) findViewById(R.id.saveButton);
    }

    void initData() {
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);
        Bundle bundle =getIntent().getExtras();
        mode = bundle.getString("mode", "");
        id = bundle.getString("id", "");
        title = bundle.getString("title", "");
        category = bundle.getString("category", "");
        url = bundle.getString("url", "");

        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
    }

    void onClickGroup() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Berita payload = new Berita();
                payload.setTitle(titleEditText.getText().toString());
                payload.setCategory(categoryEditText.getText().toString());
                payload.setUrl(urlEditText.getText().toString());
                postNews(payload);
            }
        });
    }

    private void postNews(Berita payload) {
        beritaViewModel.postBeritaRepository(payload).observe(this, newsResponse -> {
            berita =newsResponse.getData();
            finish();
        });
    }
}