package com.example.android.helloworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.SecureRandom;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    private CountryModel countryModel;
    private int keepTheObjectID;
    Context mContext;
    List<Integer> imageDisplayedID = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        imageView = findViewById(R.id.countries_flag_image);
        recyclerView = findViewById(R.id.options_recyclerView);


        try {
            getCountryData(readFromRawFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    private void getCountryData(String countryJson) {
        Gson gson = new Gson();
        countryModelList = gson.fromJson(countryJson, new TypeToken<ArrayList<CountryModel>>() {}.getType());
        displayData(countryModelList.get(0));
    }



    private void displayData(CountryModel countryModel) {
        // USE GLIDE TO LOAD THE IMAGES
        Glide.with(this)
                .load("file:///android_asset/"  + countryModel.getImageName())
                .into(imageView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new CountryOptionsListAdapter(this, this, countryModel));

        int keepTheObjectID = countryModel.getId();
    }


    @Override
    public void onItemClick(String optionChosen, int position, CountryModel countryModel) {
        if (countryModel.getCorrectAnswer().equals(optionChosen)) {
            Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show();
            getAnotherCountryData();
        } else {
            Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
            getAnotherCountryData();
        }
    }


    //Keep track of answered questions and do well not to reshow them
    //Make sure your getRandomQuestion function returns a new Question
    //Make sure it works dynamically


    //Gets a random data from the Country model

    /**This function as two flaws it is only displaying the object at the first index. Also,
     * it is only checking the Id of the object at the first index
     */
    private void getAnotherCountryData() {
       Collections.shuffle(countryModelList, new Random());
       if (countryModelList.get(0).getId()==keepTheObjectID){
           Collections.shuffle(countryModelList,new Random());
}else {
           displayData(countryModelList.get(0));
       }

}}
