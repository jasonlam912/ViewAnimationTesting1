package com.jasonstudio.viewanimationtesting1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.jasonstudio.viewanimationtesting1.ExpandableListViewClasses.ExpandableListViewAdapter;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.Food;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.FoodType;

import java.util.ArrayList;
import java.util.List;

public class ExpandLVActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<FoodType> foodTypeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_l_v);
        initData();
        initELV();
        expandableListView.expandGroup(0);
    }

    private void initData(){
        foodTypeList = new ArrayList<>();
        List<Food> foodList1 = new ArrayList<>();
        /*
        foodList1.add(new Food("foodName1", 18.41f, true));
        foodList1.add(new Food("foodName2", 18.42f, false));
        foodList1.add(new Food("foodName3", 18.43f, true));
        foodTypeList.add(new FoodType("Drinks", foodList1));
        List<Food> foodList2 = new ArrayList<>();
        foodList2.add(new Food("foodName4", 18.44f, false));
        foodList2.add(new Food("foodName5", 18.45f, true));
        foodTypeList.add(new FoodType("Rice", foodList2));
        List<Food> foodList3 = new ArrayList<>();
        foodList3.add(new Food("foodName6", 18.46f, true));
        foodList3.add(new Food("foodName7", 18.47f, true));
        foodList3.add(new Food("foodName8", 18.48f, false));
        foodList3.add(new Food("foodName9", 18.49f, false));
        foodTypeList.add(new FoodType("Noodles", foodList3));
        List<Food> foodList4 = new ArrayList<>();
        foodList4.add(new Food("foodName10", 118.46f, true));
        foodList4.add(new Food("foodName11", 218.47f, true));
        foodList4.add(new Food("foodName12", 318.48f, false));
        foodList4.add(new Food("foodName13", 418.49f, false));
        foodTypeList.add(new FoodType("Sushi", foodList4));*/
        Log.d("foodType size",Integer.toString(foodTypeList.size()));
    }

    private void initELV(){
        expandableListView = findViewById(R.id.expandableListView);
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(foodTypeList, this.getApplicationContext());
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
    }
}