package com.example.android.helloworld;

public class CountryPojo {
    String name;
    String  image;
    String imageContainer;

    public CountryPojo(String name, String image, String imageContainer){

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

    public void setImage(String image){
        this.image=image;
    }
    public String getImage(){
        return image;
    }
}
