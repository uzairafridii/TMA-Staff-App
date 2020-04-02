package com.example.tmaadminapp.AppModules.NewsFeed.AdapterOfNewsFeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.R;

import java.util.ArrayList;

public class AdapterForNewsFeed extends RecyclerView.Adapter<AdapterForNewsFeed.MyNewsViewHolder>
{
    private ArrayList<NewsFeedModel> modelArrayList;
    private Context ctx;

    public AdapterForNewsFeed(ArrayList<NewsFeedModel> modelArrayList, Context ctx) {
        this.modelArrayList = modelArrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(ctx).inflate(R.layout.news_feed_design_for_recycler_view , null);
        MyNewsViewHolder holder = new MyNewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsViewHolder holder, int position)
    {
        NewsFeedModel model = modelArrayList.get(position);

        holder.setTitleOfNews(model.getTitle());
        holder.setAuthorName(model.getAuthor());
        holder.setDateTime(model.getDateAndTime());
        holder.setReadMoreTextView(model.getDescription());

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MyNewsViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titleOfNews, dateTime , authorName;
        private ReadMoreTextView readMoreTextView;
        private View mView;

        public MyNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

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
