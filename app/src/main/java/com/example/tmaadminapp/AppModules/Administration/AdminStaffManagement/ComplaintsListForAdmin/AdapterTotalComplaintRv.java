package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.ComplaintsListForAdmin;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AdminCompaintView;

import java.util.List;

public class AdapterTotalComplaintRv extends RecyclerView.Adapter<AdapterTotalComplaintRv.MyCompaintViewHolder>
{

    private Context context;
    private List<ModelForTotalComplaints> list;
    private AdminCompaintView adminCompaintView;

    public AdapterTotalComplaintRv(Context context, List<ModelForTotalComplaints> list, AdminCompaintView adminCompaintView) {
        this.context = context;
        this.list = list;
        this.adminCompaintView = adminCompaintView;
    }

    @NonNull
    @Override
    public MyCompaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the item design layout
        View myView = LayoutInflater.from(context).inflate(R.layout.design_for_complaints_rv_admin, null);
        return new MyCompaintViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCompaintViewHolder holder, int position) {

        // data binding
        ModelForTotalComplaints complaints = list.get(position);
        holder.setTitle(complaints.getTitle());
        holder.setDescription(complaints.getDescription());
        holder.setStatus(complaints.getStatus());
        holder.setType(complaints.getField());
        holder.setDate(complaints.getDate());
        holder.setComplaintImage(complaints.getImageUrl());
        holder.setUserName(complaints.getName());


    }

    @Override
    public int getItemCount() {

        if(list.size() > 0)
        {
            adminCompaintView.onHideTextView();
            return list.size();
        }
        else
            {
            return list.size();
        }
    }

    public class MyCompaintViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, status, date, type , userName;
        private ImageView complaintImage;
        private View complaintView;

        public MyCompaintViewHolder(@NonNull View itemView) {
            super(itemView);
            complaintView = itemView;
        }


        // setter method to set item attributes
        private void setTitle(String complainTitle) {
            title = complaintView.findViewById(R.id.titleInAdminRv);
            title.setText(complainTitle);
        }

        private void setDescription(String descrip) {
            description = complaintView.findViewById(R.id.descriptionInAdminRv);
            description.setText(descrip);
        }

        private void setStatus(String complainStatus) {

            status = complaintView.findViewById(R.id.statusInAdminRv);

            if (complainStatus.equals("Pending")) {
                status.setText(complainStatus);
                status.setTextColor(Color.RED);
            }else {
                status.setText(complainStatus);
                status.setTextColor(Color.BLUE);
            }
        }


        private void setType(String complainType) {
            type = complaintView.findViewById(R.id.complainType);
            type.setText(complainType);
        }

        private void setUserName(String name) {
            userName = complaintView.findViewById(R.id.userName);
            userName.setText("Complaint By: "+name);
        }

        private void setDate(String complainDate) {
            date = complaintView.findViewById(R.id.dateInAdminRv);
            date.setText(complainDate);
        }

        private void setComplaintImage(List<String> imageUrls) {
            complaintImage = complaintView.findViewById(R.id.complainImageForAdminRv);

            Glide.with(context)
                    .load(imageUrls.get(0))
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(complaintImage);

        }


    }

}
