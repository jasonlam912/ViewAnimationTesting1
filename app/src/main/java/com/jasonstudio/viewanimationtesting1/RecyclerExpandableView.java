package com.jasonstudio.viewanimationtesting1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.FoodOpt;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.FoodOptValue;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.FoodTypeRVAdapter;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.Food;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.FoodType;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.Network.UserClientInstance;
import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.User;
import com.jasonstudio.viewanimationtesting1.customanimation.BaseItemAnimator;
import com.jasonstudio.viewanimationtesting1.customanimation.CustomRVAnimation;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class RecyclerExpandableView extends AppCompatActivity {

    private RecyclerView rv;
    private List<FoodType> foodTypeList;
    private FoodTypeRVAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_expandable_view);
        rv = findViewById(R.id.expandableRecyclerView);
        initData();
        initRecyclerView();
        initNetwork();
    }

    private void initRecyclerView(){
        adapter = new FoodTypeRVAdapter(this.getApplicationContext(), foodTypeList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rv.setItemAnimator(new BaseItemAnimator());
        //((SimpleItemAnimator)rv.getItemAnimator()).setSupportsChangeAnimations(false);
        initItemTouchHelper();
    }

    private void initData(){
        foodTypeList = new ArrayList<>();
        /*
        List<Food> foodList1 = new ArrayList<>();
        FoodOptValue foodOptValue = new FoodOptValue("fov");
        List<FoodOptValue> foodOptValues = Arrays.asList(foodOptValue.getClone(),
                foodOptValue.getClone(),foodOptValue.getClone(),foodOptValue.getClone());
        FoodOpt foodOpt = new FoodOpt("food option", foodOptValues);
        List<FoodOpt> foodOpts = Arrays.asList(foodOpt.getClone(),foodOpt.getClone(),foodOpt.getClone());
        foodList1.add(new Food("foodName1", 18.45f, true, foodOpts.stream().map(i -> i.getClone()).collect(Collectors.toList())));
        foodList1.add(new Food("foodName2", 18.45f, false, foodOpts.stream().map(i -> i.getClone()).collect(Collectors.toList())));
        foodList1.add(new Food("foodName3", 18.45f, true,  foodOpts.stream().map(i -> i.getClone()).collect(Collectors.toList())));
        foodTypeList.add(new FoodType("Drinks", foodList1));
        List<Food> foodList2 = new ArrayList<>();
        foodList2.add(new Food("foodName4", 18.45f, false,  foodOpts.stream().map(i -> i.getClone()).collect(Collectors.toList())));
        foodList2.add(new Food("foodName5", 18.45f, true,  foodOpts.stream().map(i -> i.getClone()).collect(Collectors.toList())));
        foodTypeList.add(new FoodType("Rice", foodList2));
        Log.d("foodType size",Integer.toString(foodTypeList.size()));*/
    }

    private CompositeDisposable retrofitPool;

    private void initNetwork(){
        retrofitPool = new CompositeDisposable();
        User user = new User(1,null,null);
        Observable<User> UserO = UserClientInstance.getService().getUserDetails(user);
        UserO.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<User>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull User user) {
                        foodTypeList = user.getFoodTypeList();
                        for(FoodType ft:foodTypeList){
                            ft.setNotExpanded();
                        }
                        adapter.setData(foodTypeList);
                        Log.d("getfood", foodTypeList.get(0).getFoodList().get(0).getFoodName());
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        //retrofitPool.add(d1);
    }

    private void initItemTouchHelper(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if(viewHolder instanceof FoodTypeRVAdapter.NewViewHolder) return 0;
                return super.getMovementFlags(recyclerView, viewHolder);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int i = viewHolder.getAdapterPosition();
                FoodType ft = foodTypeList.get(i);
                String name = ft.getFoodTypeName();
                //dont pass Integer in the following method, it will regard it as Object.
                foodTypeList.remove(i);
                adapter.notifyItemRemoved(i);
                Toast.makeText(RecyclerExpandableView.this, "Food type " + name + " have been removed.", Toast.LENGTH_LONG).show();
                Snackbar.make(rv, "undo the action:", Snackbar.LENGTH_LONG).setAction("Undo",  v ->{
                    foodTypeList.add(i, ft);
                    adapter.notifyItemInserted(i);
                    Log.d("food type num onswiped", Integer.toString(foodTypeList.size()));
                }).show();
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                /*if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    FoodTypeRVAdapter.ExpandableViewHolder holder = (FoodTypeRVAdapter.ExpandableViewHolder) viewHolder;
                    Log.d("ACTION SWIPE","ACTION SWIPE" + dX);
                    holder.getFoodNum().setTranslationX(dX);
                }else{
                    Log.d("ACTION Oother","ACTION Oother");
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }*/
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(rv);
    }

    @Override
    protected void onDestroy() {
        retrofitPool.dispose();
        super.onDestroy();
    }
}
