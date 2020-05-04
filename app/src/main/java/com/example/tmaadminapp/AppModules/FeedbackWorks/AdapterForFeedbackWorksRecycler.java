package com.example.tmaadminapp.AppModules.FeedbackWorks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AdapterForFeedbackWorksRecycler extends RecyclerView.Adapter<AdapterForFeedbackWorksRecycler.MyFeedbackViewHolder>
{
    private List<ModelForFeedbackWorks> feedbackWorksList;
    private Context context;

    public AdapterForFeedbackWorksRecycler(List<ModelForFeedbackWorks> feedbackWorksList, Context context) {
        this.feedbackWorksList = feedbackWorksList;
        this.context = context;
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
      final ModelForFeedbackWorks feedbackWorks  = feedbackWorksList.get(position);
      holder.setTitle(feedbackWorks.getTitle());
      holder.setDate(feedbackWorks.getCompletedDate());
      holder.setFirstWorker(feedbackWorks.getFirstWorker());
      holder.setSecondWorker(feedbackWorks.getSecondWorker());
      holder.setImage(feedbackWorks.getImageUrl());

      holder.cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

             holder.getRatingOfCurrentComplaint(feedbackWorks.getPushKey());

          }
      });
    }

    @Override
    public int getItemCount() {
        return feedbackWorksList.size();
    }

    public class MyFeedbackViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title , date , firstWorker ,secondWorker;
        private ImageView image;
        private View mView;
        private CardView cardView;

        public MyFeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            cardView = mView.findViewById(R.id.feedbackCard);
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
            Glide.with(context)
                    .load(imageUrl.get(0))
                    .into(image);
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
                    Log.d("commentSection", "onChildAdded: "+comment);

                    showCustomRatingDialog(comment , rating);
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

        // show custom dialog of rating
        private void showCustomRatingDialog(String comment , String rating)
        {
            View ratingView = LayoutInflater.from(context).inflate(R.layout.complaint_rating_dialog_design, null);
            final AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setView(ratingView);

            final AlertDialog dialog = alert.create();

            TextView textView = ratingView.findViewById(R.id.feebackComment);
            textView.setText(comment);

            TextView ratingValue = ratingView.findViewById(R.id.ratingValue);
            ratingValue.setText("( "+rating+" )");

            RatingBar ratingBar = ratingView.findViewById(R.id.feedbackRatingbar);
            ratingBar.setRating(Float.parseFloat(rating));

            ratingView.findViewById(R.id.closeFeedbackRatingBtn)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

            dialog.show();

        }
    }
}
