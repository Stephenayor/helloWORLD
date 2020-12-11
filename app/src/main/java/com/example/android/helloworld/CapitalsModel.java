package com.example.android.helloworld;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CapitalsModel {
    @SerializedName("id")
    private int id;
    @SerializedName("capital")
    private String correctAnswer;
    @SerializedName("country")
    private String countryName;
    @SerializedName("options")
    private List<String> optionsList;

    public CapitalsModel(int id, String correctAnswer, String countryName, List<String> optionsList) {
        this.id = id;
        this.correctAnswer = correctAnswer;
        this.countryName = countryName;
        this.optionsList = optionsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<String> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<String> optionsList) {
        this.optionsList = optionsList;
    }
}
