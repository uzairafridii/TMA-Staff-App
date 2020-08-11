package com.example.tmaadminapp.AppModules.ComplaintDetailAndMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.R;

import java.util.List;

public class AdapterForDetailComplaintImagesRecycler extends
        RecyclerView.Adapter<AdapterForDetailComplaintImagesRecycler.MyImagesViewHolder>
{

    private Context context;
    private List<String> imagesList;

    public AdapterForDetailComplaintImagesRecycler(Context context, List<String> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public MyImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.desgin_for_complaint_images_recycler , null);
        return new MyImagesViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyImagesViewHolder holder, int position)
    {
        final String imageUrl = imagesList.get(position);
        holder.setImageView(imageUrl);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.showImage(imageUrl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class MyImagesViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private View mView;

        public MyImagesViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }

        private void setImageView(String imageUrl)
        {
            imageView = mView.findViewById(R.id.imagesInComplaintDetailRv);

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);
        }

        public void showImage(String imageUrl)
        {
            View myView = LayoutInflater.from(context).inflate(R.layout.complaint_rating_dialog_design, null);
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setView(myView);

            ImageView imageView = myView.findViewById(R.id.noImageDialog);

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);

            alert.show();
        }
    }
}
