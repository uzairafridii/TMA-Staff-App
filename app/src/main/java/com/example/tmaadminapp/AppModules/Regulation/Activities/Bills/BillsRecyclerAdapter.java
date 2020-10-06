package com.example.tmaadminapp.AppModules.Regulation.Activities.Bills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;

import java.util.List;

public class BillsRecyclerAdapter extends RecyclerView.Adapter<BillsRecyclerAdapter.MyBillsViewHolder>
{

    private List<BillsModel> billList;
    private Context context;

    @NonNull
    @Override
    public MyBillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.desgin_for_tax_recycler, null);
        return new MyBillsViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBillsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount()
    {
        return billList.size();
    }

    public class MyBillsViewHolder extends RecyclerView.ViewHolder
    {

        public MyBillsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
