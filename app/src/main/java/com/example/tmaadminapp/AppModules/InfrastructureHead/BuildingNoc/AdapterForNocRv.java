package com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public MyNocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.design_for_noc_rv, null);
        return new MyNocViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyNocViewHolder holder, int position)
    {
        final ModelForNoc model = nocList.get(position);
        holder.setUserName(model.getUserName());
        holder.setNocTitle(model.getNocTitle());
        holder.setStatus(model.getStatus());
        holder.setDate(model.getDate());
        holder.setImage(model.getImageUrl());

        holder.nocCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (model.getStatus().equals("Registered"))
                {
                    Toast.makeText(context, "Already Registered", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    holder.changeStatus(model.getPushKey());
                }
            }
        });

     }

    @Override
    public int getItemCount() {
        return nocList.size();
    }

    public class MyNocViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userName , nocTitle , date , status;
        private ImageView image;
        private CardView nocCardView;
        private View mView;

        public MyNocViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            nocCardView = mView.findViewById(R.id.nocCardInDesign);
        }

        private void setUserName(String name)
        {
            userName = mView.findViewById(R.id.userNameInNocDesign);
            userName.setText(name);
        }

        private void setNocTitle(String title)
        {
            nocTitle = mView.findViewById(R.id.titleOfNocInDesign);
            nocTitle.setText(title);
        }

        private void setDate(String postedDate)
        {
            date  = mView.findViewById(R.id.dateInNocDesign);
            date.setText(postedDate);
        }

        private void setStatus(String nocStatus)
        {
            status = mView.findViewById(R.id.statusInNocDesign);
            if(nocStatus.equals("Registered"))
            {
              status.setText(nocStatus);
              status.setTextColor(Color.BLUE);
            }
            else
            {
                status.setTextColor(Color.RED);
                status.setText(nocStatus);
            }
        }

        private void setImage(String imageUrl)
        {
            image = mView.findViewById(R.id.nocImageInDesign);

            if(imageUrl.equals(""))
            {
                int height = 0;
                int width = 0;
                RelativeLayout.LayoutParams  params = new RelativeLayout.LayoutParams(width, height);
                image.setLayoutParams(params);
            }

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image);
        }


        public void changeStatus(String pushKey)
        {
            final DatabaseReference changeStatusRef = FirebaseDatabase.getInstance().getReference().child("Noc").child(pushKey);

            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage("Registered this Noc?");


            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Map<String , Object> data = new HashMap<>();
                    data.put("status" , "Registered");

                    changeStatusRef.updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(context, "Successfully registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });

            alert.show();
        }
    }
}
