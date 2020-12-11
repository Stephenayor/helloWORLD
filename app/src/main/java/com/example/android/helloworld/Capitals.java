package com.example.android.helloworld;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Capitals extends AppCompatActivity implements CapitalsOptionListAdapter.ItemClickListener{

    private RecyclerView recyclerView;
    private TextView textView;
    private List<CapitalsModel> capitalsModelList = new ArrayList<>();
    private CapitalsModel capitalsModel;
    private int keepTheObjectID;
    Context mContext;
    List<Integer> imageDisplayedID = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitals);

        recyclerView = findViewById(R.id.capital_options_recyclerView);


        try {
            getCapitalsData(readFromRawFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromRawFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.capitals);
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


    private void getCapitalsData(String countryJson) {
        Gson gson = new Gson();
        capitalsModelList = gson.fromJson(countryJson, new TypeToken<ArrayList<CapitalsModel>>() {}.getType());
        displayData(getRandomQuestion());
    }


    private CapitalsModel getRandomQuestion(){
        Collections.shuffle(capitalsModelList, new Random());
        return capitalsModelList.get(0);
    }

    private void displayData(CapitalsModel capitalsModel) {
        // LOAD COUNTRY CAPITALS WITH ASSETS

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new CapitalsOptionListAdapter(this, this, capitalsModel));

        int keepTheObjectID = capitalsModel.getId();
    }



    @Override
    public void onItemClick(String optionChosen, int position, CapitalsModel capitalsModel) {
        if (capitalsModel.getCorrectAnswer().equals(optionChosen)) {
            Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show();
            getAnotherCapitalData();
        } else {
            Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
            getAnotherCapitalData();
        }
    }




    //Gets a random data from the Capitals model


    private void getAnotherCapitalData() {
        Collections.shuffle(capitalsModelList, new Random());
        if (capitalsModelList.get(0).getId()==keepTheObjectID){
            Collections.shuffle(capitalsModelList,new Random());
        }else {
            displayData(capitalsModelList.get(0));
        }

    }


}
