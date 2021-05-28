package com.jasonstudio.viewanimationtesting1.ExpandableListViewClasses;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jasonstudio.viewanimationtesting1.R;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.Food;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.FoodType;

import java.util.List;


public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<FoodType> foodTypeList;
    private Context ct;

    public ExpandableListViewAdapter(List<FoodType> foodTypeList, Context ct) {
        this.foodTypeList = foodTypeList;
        this.ct = ct;
    }

    @Override
    public int getGroupCount() {
        return foodTypeList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return foodTypeList.get(groupPosition).getFoodList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return foodTypeList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return foodTypeList.get(groupPosition).getFoodList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView = LayoutInflater.from(ct).inflate(R.layout.row_food_type,null);
            TextView foodNum = convertView.findViewById(R.id.foodNum);
            foodNum.setText(foodTypeList.get(groupPosition).getFoodNum().toString());
            TextView foodTypeName = convertView.findViewById(R.id.foodTypeName);
            foodTypeName.setText(foodTypeList.get(groupPosition).getFoodTypeName());
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            Food f = (Food) getChild(groupPosition, childPosition);
            convertView = LayoutInflater.from(ct).inflate(R.layout.row_food, null);
            TextView food = convertView.findViewById(R.id.foodName);
            food.setText(f.getFoodName());
            ToggleButton inStock = convertView.findViewById(R.id.inStock);
            inStock.setChecked(f.getInStock());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
