package com.example.covidtracking.ModelClasses;

import java.util.ArrayList;

public class HelplineModel {
    public ArrayList<HelplineData> getHelplineData() {
        return helplineData;
    }

    public void setHelplineData(ArrayList<HelplineData> helplineData) {
        this.helplineData = helplineData;
    }

    ArrayList<HelplineData> helplineData=new ArrayList<>();

    public static class HelplineData {
        Integer image;
        String maintext;
        String number;

        public Integer getImage() {
            return image;
        }

        public void setImage(Integer image) {
            this.image = image;
        }

        public String getMaintext() {
            return maintext;
        }

        public void setMaintext(String maintext) {
            this.maintext = maintext;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
