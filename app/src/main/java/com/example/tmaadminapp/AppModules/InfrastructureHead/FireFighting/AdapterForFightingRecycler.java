package com.example.tmaadminapp.AppModules.InfrastructureHead.FireFighting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.AppModules.ComplaintDetailAndMap.MapActivity;
import com.example.tmaadminapp.R;

import java.util.List;

public class AdapterForFightingRecycler extends RecyclerView.Adapter<AdapterForFightingRecycler.MyViewHolder>
{
    private List<ModelForFireFighting> fireRequestList;
    private Context context;

    public AdapterForFightingRecycler(List<ModelForFireFighting> fireRequestList, Context context) {
        this.fireRequestList = fireRequestList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.design_for_fire_fighting_recycler, null);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
          final ModelForFireFighting modelForFireFighting = fireRequestList.get(0);
          holder.setUserName(modelForFireFighting.getUserName());
          holder.setDate(modelForFireFighting.getDate());

          holder.locationButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Intent intent = new Intent(context , MapActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                  intent.putExtra("lat", modelForFireFighting.getLat());
                  intent.putExtra("lng", modelForFireFighting.getLng());
                  context.startActivity(intent);


              }
          });
    }

    @Override
    public int getItemCount() {
        return fireRequestList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userName , date;
        private View mView;
        private ImageButton locationButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            locationButton = mView.findViewById(R.id.locationButton);
        }

        private void setUserName(String name)
        {
            userName = mView.findViewById(R.id.usernameInFireFighting);
            userName.setText(name);
        }

        private void setDate(String postDate)
        {
            date = mView.findViewById(R.id.dateInFireFighting);
            date.setText(postDate);
        }
    }
}
