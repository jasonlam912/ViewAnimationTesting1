package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jasonstudio.viewanimationtesting1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class FoodRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context ct;
    private List<Food> foodList;

    public FoodRVAdapter(Context ct) {
        this.ct = ct;
        this.foodList = new ArrayList<>();
    }

    public void setData(List<Food> foodList){
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position < foodList.size() ? 0:1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType){
            case 0:{
                View v = LayoutInflater.from(ct).inflate(R.layout.row_food, parent, false);
                return new FoodViewHolder(v);
            }
            case 1:{
                View v = LayoutInflater.from(ct).inflate(R.layout.row_food_type_add, parent, false);
                CardView cardView = v.findViewById(R.id.cardView);
                cardView.setCardBackgroundColor(ct.getResources().getColor(R.color.purple_400));
                return new NewViewHolder(v);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:{
                FoodViewHolder holder1 = (FoodViewHolder) holder;
                Food f = foodList.get(position);
                holder1.foodName.setText(f.getFoodName());
                holder1.inStock.setChecked(f.getInStock());
                holder1.rv.setVisibility(f.getExpanded() ? View.VISIBLE:View.GONE);
                ((FoodOptAdapter)holder1.rv.getAdapter()).setData(f.getFoodOpts());
            }
            case 1:{
            }
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size()+1;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        private TextView foodName;
        private ToggleButton inStock;
        private ImageView deleteButton;
        private CardView cardView;
        private RecyclerView rv;
        private FoodOptAdapter adapter;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            inStock = itemView.findViewById(R.id.inStock);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            cardView = itemView.findViewById(R.id.cardView);
            deleteButton.setOnClickListener(v -> {
                foodList.remove(getAdapterPosition());
                notifyDataSetChanged();
                //FoodRVAdapter.this.notifyItemRemoved(getAdapterPosition());
            });
            cardView.setOnClickListener(v -> {
                Food food = foodList.get(getAdapterPosition());
                food.setExpanded(!food.getExpanded());
                if (food.getExpanded()) {
                    notifyItemChanged(getAdapterPosition());
                } else {
                    notifyDataSetChanged();
                }
            });
            initRV();
        }

        public void initRV(){
            rv = itemView.findViewById(R.id.rv);
            adapter = new FoodOptAdapter(ct);
            rv.setAdapter(adapter);
            rv.setLayoutManager(new LinearLayoutManager(ct));
        }
    }

    public class NewViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(v -> {
                foodList.add(new Food("new food name", 29.18f, true, FoodOpt.getRandomFoodOpt()));
                FoodRVAdapter.this.notifyItemInserted(getAdapterPosition());
            });
        }
    }

}
