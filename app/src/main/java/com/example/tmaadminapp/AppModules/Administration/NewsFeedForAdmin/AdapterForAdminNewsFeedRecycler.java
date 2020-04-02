package com.example.tmaadminapp.AppModules.Administration.NewsFeedForAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.R;

import java.util.ArrayList;

public class AdapterForAdminNewsFeedRecycler extends RecyclerView.Adapter<AdapterForAdminNewsFeedRecycler.MyAdminNewsViewHodler> {

    private ArrayList<NewsFeedModel> modelArrayList;
    private Context ctx;

    public AdapterForAdminNewsFeedRecycler(ArrayList<NewsFeedModel> modelArrayList, Context ctx) {
        this.modelArrayList = modelArrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyAdminNewsViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // inflate the design layout for recycler
        View view = LayoutInflater.from(ctx).inflate(R.layout.admin_news_feed_recycler_design , null);
        MyAdminNewsViewHodler holder = new MyAdminNewsViewHodler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdminNewsViewHodler holder, int position) {

        NewsFeedModel model = modelArrayList.get(position);

        // bind values in views
        holder.setTitleOfNews(model.getTitle());
        holder.setAuthorName(model.getAuthor());
        holder.setDateTime(model.getDateAndTime());
        holder.setReadMoreTextView(model.getDescription());

        // delete button click
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ctx, "Delete Button", Toast.LENGTH_SHORT).show();
            }
        });

        // edit button click
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ctx, "Edit Button", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MyAdminNewsViewHodler extends RecyclerView.ViewHolder {

        // declare the textViews
        private TextView titleOfNews, dateTime , authorName;
        private ReadMoreTextView readMoreTextView;
        private ImageButton deleteBtn , editBtn;
        private View mView;

        public MyAdminNewsViewHodler(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            deleteBtn = mView.findViewById(R.id.deleteBtn);
            editBtn = mView.findViewById(R.id.editBtn);
        }

        // initailies and setter methods of views

        private void setTitleOfNews(String title)
        {
            titleOfNews = mView.findViewById(R.id.titleOfNewsFeed);
            titleOfNews.setText(title);
        }

        private void setAuthorName(String author)
        {
            authorName = mView.findViewById(R.id.authorName);
            authorName.setText(author);
        }

        private void setReadMoreTextView(String description)
        {
            readMoreTextView = mView.findViewById(R.id.descriptionOfNewsFeed);
            readMoreTextView.setText(description);
        }

        private void setDateTime(String dateAndTime)
        {
            dateTime = mView.findViewById(R.id.dateAndTime);
            dateTime.setText(dateAndTime);
        }
    }
}
