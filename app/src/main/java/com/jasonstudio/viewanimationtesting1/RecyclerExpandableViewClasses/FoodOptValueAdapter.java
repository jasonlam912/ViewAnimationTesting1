package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jasonstudio.viewanimationtesting1.R;

import java.util.ArrayList;
import java.util.List;

public class FoodOptValueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<FoodOptValue> foodOptValueList;
    private Context ct;

    public FoodOptValueAdapter(Context ct) {
        this.ct = ct;
        this.foodOptValueList = new ArrayList<>();
    }

    public void setData(List<FoodOptValue> foodOptValueList){
        this.foodOptValueList = foodOptValueList;
        notifyDataSetChanged();
        Log.d("foodoptvalue size", Integer.toString(foodOptValueList.size()));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ct).inflate(R.layout.item_food_opt_value, parent, false);
        return new FoodOptValueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch(holder.getItemViewType()){
            case 0:{
                FoodOptValueViewHolder holder1 = (FoodOptValueViewHolder) holder;
                holder1.foodOptValue.setText(foodOptValueList.get(position).getFoodOptValue());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return foodOptValueList.size();
    }
    public class FoodOptValueViewHolder extends RecyclerView.ViewHolder{
        private TextView foodOptValue;

        public FoodOptValueViewHolder(@NonNull View itemView) {
            super(itemView);
            foodOptValue = itemView.findViewById(R.id.foodOptValue);
        }
    }
}
