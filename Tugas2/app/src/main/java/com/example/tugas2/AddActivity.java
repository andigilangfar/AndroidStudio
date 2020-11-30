package com.example.tugas2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugas2.model.Berita;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    Button submitberitaButton;
    EditText titleEditText, descriptionEditText, urlEditText;
    ArrayList<Berita> listBerita= new ArrayList();
    private static String json="";
    private ArrayList<String> arrTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    initData();
    findViewById();
    onClickGroup();
    }

   void findViewById(){
        submitberitaButton = findViewById(R.id.submitberitaButton);
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        urlEditText = findViewById(R.id.urlEditText);
   }

   void saveData(){
       String newTitle = titleEditText.getText().toString();
       String newDescription = descriptionEditText.getText().toString();
       String newUrl = urlEditText.getText().toString();

       Berita model = new Berita();
       model.setTitle(newTitle);
       model.setDescription(newDescription);
       model.setUrl(newUrl);
       Gson gson = new Gson();
       json=gson.toJson(model);
   }
   void initData(){
       arrTemp=getIntent().getStringArrayListExtra("data");
       submitberitaButton = (Button) findViewById(R.id.submitberitaButton);
       titleEditText = (EditText) findViewById(R.id.titleEditText);
       descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
       urlEditText = (EditText) findViewById(R.id.urlEditText);
   }
   void onClickGroup(){
       submitberitaButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               saveData();
               JSONArray jsonArray = new JSONArray();
               jsonArray.put(listBerita);
               Log.v("Array: ",jsonArray.toString());
               Intent i = getIntent();
               i.putExtra("data", json);
               setResult(RESULT_OK, i);
               finish();
           }
       });
   }
}