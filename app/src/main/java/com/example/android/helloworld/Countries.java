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

public class Countries extends AppCompatActivity implements CountryJson {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        ImageView mImage;
        Button mButton;
        String name;
        String image;



        final String drawable;
        drawable="imageDisplayed";


        Button nigeria_btn = (Button) findViewById(R.id.nigeria_btn);

        try {
            //get JSON object from JSON file
            JSONObject obj = new JSONObject(JSON_STRING);
            //Fetch JSON object
            JSONObject Nigeria = obj.getJSONObject("Nigeria");
            //get Objects name and image
            name = Nigeria.getString("nigeria");
            image = Nigeria.getString("nigeria.png");
        }catch (JSONException e) {
            e.printStackTrace();
        }


        nigeria_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawable=="nigeria.png"){
                    // initiate the Toast
                    Toast toast = Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0, 0);
                    toast.show(); // display the Toast
                }else { }
            }
        });
    }
}
