package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String userName;
    @SerializedName("foodtypes")
    private List<FoodType> foodTypeList;

    public User(String userName, List<FoodType> foodTypeList) {
        this.userName = userName;
        this.foodTypeList = foodTypeList;
    }

    public User(int id, String userName, List<FoodType> foodTypeList) {
        this.id = id;
        this.userName = userName;
        this.foodTypeList = foodTypeList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<FoodType> getFoodTypeList() {
        return foodTypeList;
    }

    public void setFoodTypeList(List<FoodType> foodTypeList) {
        this.foodTypeList = foodTypeList;
    }
}
