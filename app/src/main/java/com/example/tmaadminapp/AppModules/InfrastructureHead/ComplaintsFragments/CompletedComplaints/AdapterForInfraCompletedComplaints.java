package com.example.tmaadminapp.AppModules.InfrastructureHead.ComplaintsFragments.CompletedComplaints;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.SanitationHead.ComplaintFragements.CompletedComplaints.ModelForCompletedComplaints;

import java.util.ArrayList;

public class AdapterForInfraCompletedComplaints extends RecyclerView.Adapter<AdapterForInfraCompletedComplaints.MyInfraViewHolder>
{
    private ArrayList<ModelForCompletedComplaints> listOfCompletedComplaints;
    private Context ctx;

    public AdapterForInfraCompletedComplaints(ArrayList<ModelForCompletedComplaints> listOfCompletedComplaints, Context ctx) {
        this.listOfCompletedComplaints = listOfCompletedComplaints;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public AdapterForInfraCompletedComplaints.MyInfraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(ctx).inflate(R.layout.design_for_completed_complaints_recycler , null);
        MyInfraViewHolder holder = new MyInfraViewHolder(myView);
            return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyInfraViewHolder holder, int position)
    {

        ModelForCompletedComplaints model = listOfCompletedComplaints.get(position);

        holder.setTitle(model.getTitleOfPComplaints());
        holder.setStatus(model.getStatusOfPComplaints());
        holder.setDateAndTime(model.getDataAndTimeOfPComplaints());
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
            title = mView.findViewById(R.id.completedComplaintTitleInItemDesign);
            title.setText(completeCompTitle);
        }

        public void setStatus(String completeCompStatus) {

            status = mView.findViewById(R.id.statusOfCompletedcomplaints);
            status.setTextColor(Color.RED);
            status.setText(completeCompStatus);

        }

        public void setDateAndTime(String completeCompDateAndTime) {
            dateAndTime = mView.findViewById(R.id.dateAndTimeOfCompletedComplaints);
            dateAndTime.setText(completeCompDateAndTime);
        }
    }
}
