package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses;

import android.content.Intent;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FoodOpt {
    @SerializedName("id")
    private int id;
    @SerializedName("option")
    private String foodOption;
    @SerializedName("foodoptvalues")
    private List<FoodOptValue> foodOptValues;

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    private Boolean expanded;

    public FoodOpt(String foodOption, List<FoodOptValue> foodOptValues) {
        this.foodOption = foodOption;
        this.foodOptValues = foodOptValues;
        this.expanded = false;
    }

    public String getFoodOption() {
        return foodOption;
    }

    public void setFoodOption(String foodOption) {
        this.foodOption = foodOption;
    }

    public List<FoodOptValue> getFoodOptValues() {
        return foodOptValues;
    }

    public void setFoodOptValues(List<FoodOptValue> foodOptValues) {
        this.foodOptValues = foodOptValues;
    }

    public FoodOpt getClone(){
        List<FoodOptValue> newFoodOptValues = new ArrayList<>();
        for(FoodOptValue item:this.foodOptValues){
            newFoodOptValues.add(item.getClone());
        }
        Log.d("getClone fo Valuesize", Integer.toString(newFoodOptValues.size()));
        Log.d("math digit", Long.toString(Math.round(Math.random()*100)));
        String foName = foodOption.equals("food option")?this.foodOption + Math.round(Math.random()*100):this.foodOption;
        return new FoodOpt(foName, newFoodOptValues);
    }

    public static List<FoodOpt> getRandomFoodOpt(){
        List<FoodOpt> res = new ArrayList<>();
        int optCloneNum = (int) Math.round(Math.random()*2+1);
        for(int j=0;j<optCloneNum;j++){
            FoodOptValue value = new FoodOptValue("foodOptValue");
            int valueCloneNum = (int) Math.round(Math.random()*5+1);
            List<FoodOptValue> values = new ArrayList<>();
            for(int i=0;i<valueCloneNum;i++){
                values.add(value.getClone());
            }
            FoodOpt foodOpt = new FoodOpt("food Option", values);
            res.add(foodOpt);
        }
        return res;
    }
}
