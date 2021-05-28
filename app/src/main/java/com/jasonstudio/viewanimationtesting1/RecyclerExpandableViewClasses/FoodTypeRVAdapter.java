package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses;

import android.content.Context;
import android.os.Handler;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.google.android.material.snackbar.Snackbar;
import com.jasonstudio.viewanimationtesting1.R;
import com.jasonstudio.viewanimationtesting1.customanimation.BaseItemAnimator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FoodTypeRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ct;
    private List<FoodType> foodTypeList;
    //private RecyclerView.RecycledViewPool viewPool;

    public FoodTypeRVAdapter(Context ct, List<FoodType> foodTypeList) {
        this.ct = ct;
        this.foodTypeList = foodTypeList;
        //viewPool = new RecyclerView.RecycledViewPool();
    }

    public void setData(List<FoodType> foodTypeList){
        this.foodTypeList = foodTypeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType){
            case 0:{
                View view = LayoutInflater.from(ct).inflate(R.layout.row_food_type, parent, false);
                return new ExpandableViewHolder(view);
            }
            case 1:{
                View view = LayoutInflater.from(ct).inflate(R.layout.row_food_type_add, parent, false);
                return new NewViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:{
                ExpandableViewHolder holder1 = (ExpandableViewHolder) holder;
                FoodType foodType = foodTypeList.get(position);
                holder1.expandableRV.setVisibility(foodType.getExpanded()? View.VISIBLE: View.GONE);
                //holder1.foodNum.setText(Integer.toString(foodType.getFoodNum()));
                holder1.foodTypeName.setText(foodType.getFoodTypeName());
                Log.d("onBind food type pos", Integer.toString(position));
                //holder1.expandableRV.setRecycledViewPool(viewPool);
                holder1.adapter.setData(foodType.getFoodList());
            }
            case 1:{
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position<foodTypeList.size()?0:1;
    }

    @Override
    public int getItemCount() {
        return foodTypeList.size() +1;
    }

    public class ExpandableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private TextView foodNum;
        private TextView foodTypeName;
        private RecyclerView expandableRV;
        private CardView foodTypeCard;
        private ImageView deleteButton;
        private FoodRVAdapter adapter;

        public ExpandableViewHolder(@NonNull View itemView) {
            super(itemView);
            //foodNum = itemView.findViewById(R.id.foodNum);
            foodTypeName = itemView.findViewById(R.id.foodTypeName);
            foodTypeCard = itemView.findViewById(R.id.foodTypeCard);
            foodTypeCard.setOnClickListener(this);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(v -> deleteItem(v));
            expandableRV = itemView.findViewById(R.id.expandableRecyclerView);
            //((SimpleItemAnimator) expandableRV.getItemAnimator()).setSupportsChangeAnimations(false);
            adapter = new FoodRVAdapter(ct);
            expandableRV.setAdapter(adapter);
            expandableRV.setItemAnimator(new BaseItemAnimator());
            //expandableRV.getItemAnimator().setRemoveDuration(0l);
            expandableRV.setLayoutManager(new LinearLayoutManager(ct));
            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                @Override
                public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                    if(viewHolder instanceof FoodRVAdapter.NewViewHolder) return 0;
                    return super.getMovementFlags(recyclerView, viewHolder);
                }

                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int inner_i = viewHolder.getAdapterPosition();
                    int outer_i = getAdapterPosition();
                    Food f = foodTypeList.get(outer_i).getFoodList().get(inner_i);
                    foodTypeList.get(outer_i).getFoodList().remove(inner_i);
                    expandableRV.getAdapter().notifyDataSetChanged();
                    //expandableRV.getAdapter().notifyItemRemoved(inner_i);
                    notifyItemChanged(outer_i);
                    SnackbarUndo.showSnackbarUndo(FoodTypeRVAdapter.this,expandableRV, expandableRV.getAdapter(),outer_i , inner_i, f, foodTypeList.get(outer_i).getFoodList());
                }
            }).attachToRecyclerView(expandableRV);

        }
        private void deleteItem(View v){
            Log.d("deleteItem", Integer.toString(this.getAdapterPosition()));
            int i = this.getAdapterPosition();
            FoodType ft = foodTypeList.get(i);
            foodTypeList.remove(i);
            notifyItemRemoved(i);
            Snackbar.make(v, "undo the action:", Snackbar.LENGTH_LONG).setAction("Undo", a ->{
                foodTypeList.add(i, ft);
                notifyItemInserted(i);
                //Log.d("food type num onswiped", Integer.toString(foodTypeList.size()));
            }).show();
            deleteButton.setEnabled(false);
            new Handler().postDelayed (() -> deleteButton.setEnabled(true), new BaseItemAnimator().getRemoveDuration());
        }

        @Override
        public void onClick(View v) {
            FoodType thisFT = foodTypeList.get(getAdapterPosition());
            thisFT.setExpanded(!thisFT.getExpanded());
            Log.d("expanded", Boolean.toString(!thisFT.getExpanded()));
            notifyItemChanged(getAdapterPosition());
        }
    }

    public class NewViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(v -> {
                foodTypeList.add(new FoodType("new food type", new ArrayList<>()));
                FoodTypeRVAdapter.this.notifyItemInserted(foodTypeList.size()-1);
            });
        }
    }
}
