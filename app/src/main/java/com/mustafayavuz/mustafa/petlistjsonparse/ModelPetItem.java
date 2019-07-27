package com.mustafayavuz.mustafa.petlistjsonparse;

import java.util.ArrayList;

public class ModelPetItem {

    private String myUsername;
    private String mySpecies;
    private String myPhoto;
    private String myFavFoods;
    private int myBirthDay;

    public ModelPetItem(String username, String species, String photo, String favFoods, int birthDay) {
        myUsername = username;
        mySpecies = species;
        myFavFoods = favFoods;
        myBirthDay = birthDay;
        myPhoto = photo;
    }

    public String getMyUsername() {
        return myUsername;
    }

    public String getMySpecies() {
        return mySpecies;
    }

    public String getMyFavFoods() {
        return myFavFoods;
    }

    public int getMyBirthDay() {
        return myBirthDay;
    }

    public String getMyPhoto() {
        return myPhoto;
    }
}
