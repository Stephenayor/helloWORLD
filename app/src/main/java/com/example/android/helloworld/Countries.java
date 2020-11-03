package com.example.android.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;

public class Countries extends AppCompatActivity implements CountryJson {

    private  String  mName;
    private int image;
    private boolean imageContainer;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        Button nigeria_btn = (Button) findViewById(R.id.nigeria_btn);
         imageView = (ImageView) findViewById(R.id.country_image);


        Glide.with(this)
            .load("nigeria.png")
                .into(R.id.country_image);


        try {
            //get JSON object from JSON file
            JSONObject obj = new JSONObject(JSON_STRING);
            //Fetch JSON object
            JSONObject Nigeria = obj.getJSONObject("Nigeria");
            //get Objects name and image
            mName = Nigeria.getString("nigeria");
            image = Nigeria.getInt("nigeria.png");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

//        @Override
//                public boolean equals(Object o);
//                Countries country = (Countries)o;
//                return imageContainer == country.imageContainer  && "nigeria.png";


            public void checkImage(View v) {
                if(imageContainer.equals("nigeria.png")){
                    // display this Toast
                    Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0, 0);
                    toast.show(); // show Toast
                }else { }
            }
        }





