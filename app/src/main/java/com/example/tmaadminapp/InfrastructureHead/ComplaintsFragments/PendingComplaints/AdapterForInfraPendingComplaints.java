package com.example.tmaadminapp.InfrastructureHead.ComplaintsFragments.PendingComplaints;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.SanitationHead.ComplaintFragements.CompletedComplaints.AddCompletedWorkOnComplaint;
import com.example.tmaadminapp.SanitationHead.ComplaintFragements.PendingComplaints.AdapterForPComplaintRecycler;
import com.example.tmaadminapp.SanitationHead.ComplaintFragements.PendingComplaints.ModelForPendingComplaints;

import java.util.ArrayList;

public class AdapterForInfraPendingComplaints extends RecyclerView.Adapter<AdapterForInfraPendingComplaints.MyViewHolder> {

    private ArrayList<ModelForPendingComplaints> listOfPComplaints;
    private Context ctx;

    public AdapterForInfraPendingComplaints(ArrayList<ModelForPendingComplaints> listOfPComplaints, Context ctx) {
        this.listOfPComplaints = listOfPComplaints;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(ctx).inflate(R.layout.design_for_pending_complaints , null);
        MyViewHolder holder = new MyViewHolder(myView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position)
    {


        ModelForPendingComplaints model = listOfPComplaints.get(position);

        holder.setTitle(model.getTitleOfPComplaints());
        holder.setDesc(model.getDescriptionOfPComplaints());
        holder.setStatus(model.getStatusOfPComplaints());
        holder.setDateAndTime(model.getDataAndTimeOfPComplaints());

        holder.optionMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(ctx , holder.optionMenuBtn);
                popupMenu.inflate(R.menu.option_menu_for_infra_complaints);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem)
                    {
                        switch (menuItem.getItemId())
                        {
                            case R.id.moveToSanitation:
                            {

                                Toast.makeText(ctx, "Sanitation", Toast.LENGTH_SHORT).show();
                                break;
                            }

                            case R.id.completedInfra:
                            {
                                ctx.startActivity(new Intent(ctx , AddCompletedWorkOnComplaint.class));
                                break;
                            }

                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });




    }

    @Override
    public int getItemCount() {
        return listOfPComplaints.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title , desc , status , dateAndTime;
        private View mView;
        private ImageButton optionMenuBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            optionMenuBtn = mView.findViewById(R.id.moreIconButtonInPendingComplaintItemDesign);
        }

        private void setTitle(String pCompTitle)
        {
            title = mView.findViewById(R.id.pendingComplaintTitleInItemDesign);
            title.setText(pCompTitle);
        }

        public void setDesc(String pCompDesc) {
            desc = mView.findViewById(R.id.pComplaintdescriptionInItemDesign);
            desc.setText(pCompDesc);
        }

        public void setStatus(String pCompStatus) {

            status = mView.findViewById(R.id.statusOfPcomplaints);
            status.setTextColor(Color.RED);
            status.setText(pCompStatus);

        }

        public void setDateAndTime(String pCompDateAndTime) {
            dateAndTime = mView.findViewById(R.id.dateAndTimeOfPComplaints);
            dateAndTime.setText(pCompDateAndTime);
        }
    }
}
