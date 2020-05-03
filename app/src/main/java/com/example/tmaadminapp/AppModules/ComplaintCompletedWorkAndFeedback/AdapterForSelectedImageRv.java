package com.example.tmaadminapp.AppModules.ComplaintCompletedWorkAndFeedback;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;

import java.util.List;

public class AdapterForSelectedImageRv extends
        RecyclerView.Adapter<AdapterForSelectedImageRv.MyImagesViewHolder>
{
    private List<Uri> imagesList;
    private Context context;

    public AdapterForSelectedImageRv(List<Uri> imagesList, Context context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.design_for_selected_images_rv , null);
        return new MyImagesViewHolder(myView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyImagesViewHolder holder, final int position)
    {
        final Uri model = imagesList.get(position);

        holder.setImage(model);
        holder.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imagesList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,imagesList.size());

            }
        });

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class MyImagesViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView image;
        private ImageButton closeBtn;
        private View mView;


        public MyImagesViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mView = itemView;
            closeBtn = mView.findViewById(R.id.imageExitButton);
        }

        private void setImage(Uri imageUri)
        {
            image = mView.findViewById(R.id.selectedImages);
            image.setImageURI(imageUri);
        }



    }
}
