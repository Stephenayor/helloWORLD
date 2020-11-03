package com.example.android.helloworld;

import android.widget.ImageView;

public class CountryPojo {
    String name;
    int image;
    private ImageView imageContainer;

    public CountryPojo(String name,  int image, ImageView imageContainer){

        this.name = name;
        this.image= image;
        this.imageContainer= imageContainer;

    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setImage(int image){
        this.image=image;
    }
    public int getImage(){
        return image;
    }

    public void setImageContainer(ImageView imageContainer){
        this.imageContainer=imageContainer;
    }
    public ImageView getImageContainer() {
        return imageContainer;
    }

}
