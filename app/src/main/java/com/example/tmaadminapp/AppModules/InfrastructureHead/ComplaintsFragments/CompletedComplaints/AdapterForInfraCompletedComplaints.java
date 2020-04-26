package com.example.tmaadminapp.AppModules.InfrastructureHead.ComplaintsFragments.CompletedComplaints;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.AppModules.SanitationHead.SanitationComplaints.ModelForComplaints;
import com.example.tmaadminapp.R;

import java.util.ArrayList;

public class AdapterForInfraCompletedComplaints extends RecyclerView.Adapter<AdapterForInfraCompletedComplaints.MyInfraViewHolder>
{
    private ArrayList<ModelForComplaints> listOfCompletedComplaints;
    private Context ctx;

    public AdapterForInfraCompletedComplaints(ArrayList<ModelForComplaints> listOfCompletedComplaints, Context ctx) {
        this.listOfCompletedComplaints = listOfCompletedComplaints;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public AdapterForInfraCompletedComplaints.MyInfraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(ctx).inflate(R.layout.design_for_sanit_and_infra_complaints , null);
        MyInfraViewHolder holder = new MyInfraViewHolder(myView);
            return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyInfraViewHolder holder, int position)
    {

        ModelForComplaints model = listOfCompletedComplaints.get(position);

        holder.setTitle(model.getTitle());
        holder.setStatus(model.getStatus());
        holder.setDateAndTime(model.getDate());
    }

    @Override
    public int getItemCount() {
        return listOfCompletedComplaints.size();
    }

    public class MyInfraViewHolder extends RecyclerView.ViewHolder {

        private TextView title , status , dateAndTime;
        private View mView;

        public MyInfraViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        private void setTitle(String completeCompTitle)
        {
            title = mView.findViewById(R.id.sanitationComplaintsTitleInItemDesign);
            title.setText(completeCompTitle);
        }

        public void setStatus(String completeCompStatus) {

            status = mView.findViewById(R.id.statusOfSanitationComplaints);
            status.setTextColor(Color.RED);
            status.setText(completeCompStatus);

        }

        public void setDateAndTime(String completeCompDateAndTime) {
            dateAndTime = mView.findViewById(R.id.dateAndTimeOfSanitationComplaints);
            dateAndTime.setText(completeCompDateAndTime);
        }
    }
}
