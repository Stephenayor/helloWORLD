package com.example.android.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Countries extends AppCompatActivity implements CountryOptionsListAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private ImageView imageView;
    private List<CountryModel> countryModelList = new ArrayList<>();
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        try {
            getCountryData(readFromRawFile());
        } catch (IOException e) {
            e.printStackTrace();
        }



        //TODO : initialize the imageview

        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.nigeria);
        recyclerView = findViewById(R.id.countries_recyclerView);
    }

    private void getCountryData(String countryJson) {
        Gson gson = new Gson();
        countryModelList = gson.fromJson(countryJson, new TypeToken<ArrayList<CountryModel>>(){}.getType());
        displayData();
    }

    private void displayData() {
        //TODO : USE GLIDE TO LOAD THE IMAGES
        Glide.with(this)
                .load(imageView)
                .into(imageView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setAdapter(new CountryOptionsListAdapter(mContext, countryModelList, this));

    }

    private String readFromRawFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.countries);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            inputStream.close();
        }
        return writer.toString();
    }


    @Override
    public void onItemClick(String optionChosen, int position) {
        if (countryModelList.get(0).correctAnswer.equals(optionChosen)){
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, "Wrong", Toast.LENGTH_SHORT).show();
            getAnotherCountryData();
        }
    }

    //Gets a random data from the Country model
    private void getAnotherCountryData() {
        Collections.shuffle(countryModelList, new Random());
        displayData();
    }

}




