package com.example.tmaadminapp.AppModules.Complaints;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.AppModules.AddComplaintCompletedWork.AddCompletedWorkActivity;
import com.example.tmaadminapp.AppModules.ComplaintDetailAndMap.ComplaintDetails;
import com.example.tmaadminapp.AppModules.ComplaintDetailAndMap.MapActivity;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.ComplaintsView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterForComplaintRv extends
        RecyclerView.Adapter<AdapterForComplaintRv.MyComplaintViewHolder> {
    private List<ModelForComplaints> listOfPComplaints;
    private Context ctx;
    private ComplaintsView complaintsView;
    private DatabaseReference dbRef;

    public AdapterForComplaintRv(List<ModelForComplaints> listOfPComplaints,
                                 Context ctx, ComplaintsView complaintsView) {
        this.listOfPComplaints = listOfPComplaints;
        this.ctx = ctx;
        this.complaintsView = complaintsView;
        Log.d("constructor", "AdapterForComplaintRv: constructor ");
    }

    @NonNull
    @Override
    public MyComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(ctx).inflate(R.layout.design_for_sanit_and_infra_complaints, null);
        MyComplaintViewHolder holder = new MyComplaintViewHolder(myView);
        Log.d("view holder", "onCreateViewHolder: ");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyComplaintViewHolder holder, int position) {
        final ModelForComplaints model = listOfPComplaints.get(position);

        holder.setTitle(model.getTitle());
        holder.setDesc(model.getDescription());
        holder.setStatus(model.getStatus());
        holder.setDateAndTime(model.getDate());
        holder.setImages(model.getImageUrl());
        holder.setComplaintPersonName(model.getName());

        Log.d("titleOfComplaint", "onBindViewHolder: " + model.getTitle());

        // click on over flow menu button
        holder.optionMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final PopupMenu popupMenu = new PopupMenu(ctx, holder.optionMenuBtn);
                popupMenu.inflate(R.menu.option_menu_for_complaints);

                // current post data base reference
                dbRef = FirebaseDatabase.getInstance().getReference().child("Complaints").child(model.getPushKey());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.changeComplaintType:
                                {
                                holder.changeComplaintType(model.getField());
                                break;
                            }

                            case R.id.completed: {
                                holder.markAsCompleted(model.getTitle(), model.getPushKey(),
                                        model.getUid(), model.getField() , model.getStatus());
                                break;
                            }

                            case R.id.showDetails: {
                                holder.showComplaintDetails(model.getTitle(), model.getDescription(), model.getImageUrl());

                                break;
                            }

                            case R.id.showLocation: {
                                holder.showComplaintLocation(model.getLatitude(), model.getLongitude());
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

        if (listOfPComplaints.size() > 0) {
            complaintsView.onHideTextNoItem();
            return listOfPComplaints.size();
        } else {
            return listOfPComplaints.size();
        }
    }

    // view holder class
    public class MyComplaintViewHolder extends RecyclerView.ViewHolder {
        private TextView title, desc, status, dateAndTime, complaintPersonName;
        private ImageView complaintImages;
        private View mView;
        private ImageButton optionMenuBtn;

        public MyComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            optionMenuBtn = mView.findViewById(R.id.moreIconButtonInSanitationComplaints);
        }


        private void setComplaintPersonName(String name) {
            complaintPersonName = mView.findViewById(R.id.complaintPersonName);
            complaintPersonName.setText("Complaint By : " + name);
        }

        private void setTitle(String pCompTitle) {
            title = mView.findViewById(R.id.sanitationComplaintsTitleInItemDesign);
            title.setText(pCompTitle);
        }

        private void setDesc(String pCompDesc) {
            desc = mView.findViewById(R.id.sanitationComplaintsdescription);
            desc.setText(pCompDesc);
        }

        private void setStatus(String compStatus) {

            status = mView.findViewById(R.id.statusOfSanitationComplaints);

            if (compStatus.equals("Pending")) {
                status.setTextColor(Color.RED);
                status.setText(compStatus);
            } else {
                status.setTextColor(Color.BLUE);
                status.setText(compStatus);
            }


        }

        private void setDateAndTime(String pCompDateAndTime) {
            dateAndTime = mView.findViewById(R.id.dateAndTimeOfSanitationComplaints);
            dateAndTime.setText(pCompDateAndTime);
        }

        private void setImages(List<String> urls) {
            complaintImages = mView.findViewById(R.id.sanitationComplaintsImageInItemDesign);

            Log.d("imageUrl", "setImages: " + urls.get(0));

            Glide.with(ctx)
                    .load(urls.get(0))
                    .placeholder(R.drawable.tma_logo)
                    .into(complaintImages);


        }

        public void changeComplaintType(final String type)
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
            alert.setTitle("Changing Department");
            alert.setMessage("Do you want to change the complaint department");
            alert.setCancelable(false);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, int which) {

                    Map<String, Object> changeType = new HashMap<>();
                    if(type.equals("Sanitation"))
                    {
                        changeType.put("field", "Infrastructure");
                    }
                    else
                    {
                        changeType.put("field", "Sanitation");
                    }

                    dbRef.updateChildren(changeType).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(ctx, "Complaint type is successfully change", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                        }
                    });


                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alert.show();


        }

        public void markAsCompleted(final String title, final String pushKey,
                                    final String uid, final String depart , final String status)
        {
            if(status.equals("Pending")) {
                Intent dataIntent = new Intent(ctx, AddCompletedWorkActivity.class);
                dataIntent.putExtra("title", title);
                dataIntent.putExtra("pushKey", pushKey);
                dataIntent.putExtra("uid", uid);
                dataIntent.putExtra("department", depart);
                ctx.startActivity(dataIntent);
            }
            else
            {
                Toast.makeText(ctx, "Already Completed", Toast.LENGTH_SHORT).show();
            }
        }


        public void showComplaintDetails(String title, String description, List<String> imageUrl)
        {
            Intent dataIntent = new Intent(ctx, ComplaintDetails.class);
            dataIntent.putExtra("title", title);
            dataIntent.putExtra("description", description);
            dataIntent.putExtra("imageUrls", (Serializable) imageUrl);
            ctx.startActivity(dataIntent);

        }

        public void showComplaintLocation(double latitude, double longitude) {
            Intent dataIntent = new Intent(ctx, MapActivity.class);
            dataIntent.putExtra("lat", latitude);
            dataIntent.putExtra("lng", longitude);
            ctx.startActivity(dataIntent);

        }

    }
}
