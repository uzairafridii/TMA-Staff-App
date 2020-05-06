package com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterForNocRv extends RecyclerView.Adapter<AdapterForNocRv.MyNocViewHolder>
{
    private List<ModelForNoc> nocList;
    private Context context;

    public AdapterForNocRv(List<ModelForNoc> nocList, Context context) {
        this.nocList = nocList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyNocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNocViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return nocList.size();
    }

    public class MyNocViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userName , nocTitle , date , image , status;
        private View mView;

        public MyNocViewHolder(@NonNull View itemView) {
            super(itemView);
        }




    }
}
