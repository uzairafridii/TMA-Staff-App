package com.example.tmaadminapp.AppModules.WorkersListAndDetails.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.AppModules.WorkersListAndDetails.Models.ModelForWorkersRating;
import com.example.tmaadminapp.R;

import java.util.List;

public class AdapterForTotalRatingRecycler extends RecyclerView.Adapter<AdapterForTotalRatingRecycler.MyRatingViewHolder>
{
     private List<ModelForWorkersRating> ratingList;
     private Context context;

    public AdapterForTotalRatingRecycler(List<ModelForWorkersRating> ratingList, Context context) {
        this.ratingList = ratingList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyRatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.design_for_workers_rating_detail_recycler, null);
        return new MyRatingViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRatingViewHolder holder, int position)
    {
        ModelForWorkersRating workersRating = ratingList.get(position);

        holder.setUserName(workersRating.getUserName());
        holder.setUserRating(workersRating.getUser_rating());
        holder.setDate(workersRating.getDate());
        holder.setUserComment(workersRating.getComment());
        holder.setCommentImage(workersRating.getImage());
    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }

    public class MyRatingViewHolder extends RecyclerView.ViewHolder
    {
        private TextView userName , userRating , userComment , date;
        private ImageView commentImage;
        private View mView;

        public MyRatingViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mView = itemView;
        }

        private void setUserName(String name)
        {
            userName = mView.findViewById(R.id.userName);
            userName.setText(name);
        }

        private void setUserRating(String rating)
        {
            userRating = mView.findViewById(R.id.userRating);
            userRating.setText(rating);
        }

        private void setUserComment(String comment)
        {
            userComment = mView.findViewById(R.id.userComment);
            userComment.setText(comment);
        }

        private void setDate(String ratingDate)
        {
            date = mView.findViewById(R.id.ratingDate);
            date.setText(ratingDate);
        }

        private void setCommentImage(String imageUrl)
        {
            commentImage = mView.findViewById(R.id.commentImage);

            if(imageUrl.equals(""))
            {
                int width = 0;
                int height = 0;
                RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);
                commentImage.setLayoutParams(parms);
            }
            else
            {
                Glide.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(commentImage);
            }

        }
    }
}
