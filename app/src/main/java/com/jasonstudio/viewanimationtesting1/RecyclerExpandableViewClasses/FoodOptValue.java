package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses;

import com.google.gson.annotations.SerializedName;

public class FoodOptValue {
    @SerializedName("id")
    private int id;
    @SerializedName("value")
    private String foodOptValue;

    public FoodOptValue(String foodOptValue) {
        this.foodOptValue = foodOptValue;
    }

    public String getFoodOptValue() {
        return foodOptValue;
    }

    public void setFoodOptValue(String foodOptValue) {
        this.foodOptValue = foodOptValue;
    }

    public FoodOptValue getClone(){
        String fovName = this.foodOptValue.equals("fov")?this.foodOptValue +  Math.round(Math.random()*100):this.foodOptValue;
        return new FoodOptValue(fovName);
    }
}
