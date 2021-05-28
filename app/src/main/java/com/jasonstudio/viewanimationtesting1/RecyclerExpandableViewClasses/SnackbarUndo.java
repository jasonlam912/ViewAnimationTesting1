package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

class SnackbarUndo {
    public static <A extends RecyclerView.Adapter, B extends RecyclerView.Adapter,T> void showSnackbarUndo(B parentAdapter, RecyclerView v, A adapter, int outerI, int innerI, T datum, List<T> list){
        Snackbar.make(v, "Undo the action:", Snackbar.LENGTH_LONG).setAction("Undo", v2 ->{
            list.add(innerI, datum);
            adapter.notifyItemInserted(innerI);
            parentAdapter.notifyItemChanged(outerI);
            //outerA.notifyItemChanged(outerI);
        }).show();
    }
}
