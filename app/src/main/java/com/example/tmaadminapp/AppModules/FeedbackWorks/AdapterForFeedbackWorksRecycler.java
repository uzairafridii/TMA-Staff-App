package com.example.tmaadminapp.AppModules.FeedbackWorks;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.FeedbackWorkView;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class AdapterForFeedbackWorksRecycler extends RecyclerView.Adapter<AdapterForFeedbackWorksRecycler.MyFeedbackViewHolder>
{
    private List<ModelForFeedbackWorks> feedbackWorksList;
    private Context context;
    private FeedbackWorkView feedbackWorkView;

    public AdapterForFeedbackWorksRecycler(List<ModelForFeedbackWorks> feedbackWorksList,
                                           Context context, FeedbackWorkView feedbackWorkView) {
        this.feedbackWorksList = feedbackWorksList;
        this.context = context;
        this.feedbackWorkView = feedbackWorkView;
    }

    @NonNull
    @Override
    public AdapterForFeedbackWorksRecycler.MyFeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.design_for_feedback_work_recycler , null);
        return new MyFeedbackViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterForFeedbackWorksRecycler.MyFeedbackViewHolder holder, int position)
    {

        //collapse all the layout first
        for(int i=0; i<feedbackWorksList.size(); i++)
        {
            holder.expandableRelativeLayout.collapse();
        }

      final ModelForFeedbackWorks feedbackWorks  = feedbackWorksList.get(position);
      holder.setTitle(feedbackWorks.getTitle());
      holder.setDate(feedbackWorks.getCompletedDate());
      holder.setFirstWorker(feedbackWorks.getFirstWorker());
      holder.setSecondWorker(feedbackWorks.getSecondWorker());
      holder.setImage(feedbackWorks.getImageUrl());

      holder.cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              // if layout is expanded then collapse it else expand it
               if(holder.expandableRelativeLayout.isExpanded())
               {
                   holder.toggleImage.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                   holder.expandableRelativeLayout.toggle();
               }
               else
               {
                   holder.toggleImage.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                   holder.expandableRelativeLayout.toggle();
                   holder.getRatingOfCurrentComplaint(feedbackWorks.getPushKey());
               }


          }
      });
    }

    @Override
    public int getItemCount() {
        if(feedbackWorksList.size() > 0)
        {
            feedbackWorkView.hideLayout();
            return feedbackWorksList.size();
        }
        else
        {
            feedbackWorkView.showLayout();
            return feedbackWorksList.size();
        }

    }

    public class MyFeedbackViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title , date , firstWorker ,secondWorker, feedbackComplaintComment, rating;
        private ImageView image, toggleImage;
        private View mView;
        private CardView cardView;
        private RatingBar complaintFeedbackRating;
        private ExpandableRelativeLayout expandableRelativeLayout;

        public MyFeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            cardView = mView.findViewById(R.id.feedbackCard);
            expandableRelativeLayout = mView.findViewById(R.id.expandedLayout);
            toggleImage = mView.findViewById(R.id.toggleImage);
        }

        private void setComplaintFeedbackRating(float rating)
        {
            complaintFeedbackRating = mView.findViewById(R.id.complaintRating);
            complaintFeedbackRating.setRating(rating);
        }

        private void setFeedbackComplaintComment(String comment)
        {
            feedbackComplaintComment = mView.findViewById(R.id.feedbackComment);
            feedbackComplaintComment.setText(comment);

        }

        private void setTitle(String complaintTitle)
        {
            title = mView.findViewById(R.id.completedComplaintTitle);
            title.setText(complaintTitle);

        }

        private void setDate(String completedDate)
        {
            date = mView.findViewById(R.id.completedComplaintdate);
            date.setText(completedDate);
        }

        private void setFirstWorker(String workerFirst)
        {
            firstWorker = mView.findViewById(R.id.workerFirst);
            firstWorker.setText(workerFirst);
        }

        private void setSecondWorker(String workerSecond)
        {
            secondWorker = mView.findViewById(R.id.workerSecond);
            secondWorker.setText("And "+workerSecond);
        }

        private void setImage(List<String> imageUrl)
        {
            image = mView.findViewById(R.id.completedComplaintImage);

            if(imageUrl.equals("") || imageUrl.isEmpty())
            {
                image.setLayoutParams(new RelativeLayout.LayoutParams(0,0));
            }
            else {
                Glide.with(context)
                        .load(imageUrl.get(0))
                        .centerCrop()
                        .into(image);
            }
        }

        private void setRating(String complaintRating)
        {
            rating = mView.findViewById(R.id.rating);
            rating.setText(complaintRating);

        }

        // get rating of current complaint
        public void getRatingOfCurrentComplaint(String pushKey)
        {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Ratings");

            Query ratingQuery = dbRef.orderByChild("complaint_key").equalTo(pushKey);

            ratingQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {
                         String comment = dataSnapshot.child("comment").getValue().toString();
                         String rating = dataSnapshot.child("user_rating").getValue().toString();
                         Log.d("commentSection", "onChildAdded: " + comment);

                         setExpandedLayout(comment, rating);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });

        }

        // expand layout of rating
        private void setExpandedLayout(String comment , String rating)
        {
            setFeedbackComplaintComment(comment);
            setComplaintFeedbackRating(Float.parseFloat(rating));
            setRating(rating);

        }
    }
}
