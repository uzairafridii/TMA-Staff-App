package com.example.tmaadminapp.AppModules.Administration.NewsFeedForAdmin;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AdminNewsView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterForAdminNewsFeedRecycler extends RecyclerView.Adapter<AdapterForAdminNewsFeedRecycler
        .MyAdminNewsViewHodler> {

    private AdminNewsView adminNewsFeedView;
    private List<NewsFeedModel> modelArrayList;
    private Context ctx;
    private DatabaseReference databaseReference;

    public AdapterForAdminNewsFeedRecycler(List<NewsFeedModel> modelArrayList, Context ctx,
                                           AdminNewsView adminNewsFeedView, DatabaseReference databaseReference) {
        this.modelArrayList = modelArrayList;
        this.ctx = ctx;
        this.databaseReference = databaseReference;
        this.adminNewsFeedView = adminNewsFeedView;
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
    public void onBindViewHolder(@NonNull final MyAdminNewsViewHodler holder, int position) {

        NewsFeedModel model = modelArrayList.get(position);

        // bind values in views
        holder.setTitleOfNews(model.getTitle());
        holder.setAuthorName(model.getAuthor());
        holder.setDateTime(model.getDateAndTime());
        holder.setReadMoreTextView(model.getDescription());

        final String pushKey = model.getPushKey();
        // delete button click
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.deleteCurrentValue(pushKey);

            }
        });

        // edit button click
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.updateValueDialog(pushKey);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (modelArrayList.size() > 0) {
            adminNewsFeedView.hideLayout();
            return modelArrayList.size();

        } else {
            adminNewsFeedView.showLayout();
            return modelArrayList.size();
        }
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


        // delete value method

        private void deleteCurrentValue(final String key)
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(mView.getRootView().getContext());
            alert.setMessage("Do you want to delete news?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i) {


                    Query query = databaseReference.orderByChild("pushKey").equalTo(key);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            // remove current item
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                data.getRef().removeValue();

                                dialogInterface.dismiss();
                                modelArrayList.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getAdapterPosition(), modelArrayList.size());
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

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

        /// update value dialog
        private void updateValueDialog(final String refKey) {
            View myView = LayoutInflater.from(ctx).inflate(R.layout.add_news_feed_by_admin, null);
            AlertDialog.Builder alert = new AlertDialog.Builder(mView.getRootView().getContext());
            alert.setView(myView);

            final AlertDialog dialog = alert.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            // change button and text view text
            TextView title = myView.findViewById(R.id.postNewsFeed);
            title.setText("Upate Post");

            Button button = myView.findViewById(R.id.post_news_feed_submit);
            button.setText("Update");
            button.setTextSize(14);

            final EditText edTitle, edDescp;
            edTitle = myView.findViewById(R.id.news_title_in_admin);
            edDescp = myView.findViewById(R.id.news_description_in_admin);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!edTitle.getText().toString().isEmpty() && !edDescp.getText().toString().isEmpty())
                    {
                        adminNewsFeedView.onShowProgressBar();

                        HashMap<String, Object> updateData = new HashMap<>();
                        updateData.put("title", edTitle.getText().toString());
                        updateData.put("description", edDescp.getText().toString());

                        databaseReference.child(refKey).updateChildren(updateData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    adminNewsFeedView.onShowMessage("Successfully updated");
                                    dialog.dismiss();
                                    adminNewsFeedView.onHideProgressBar();
                                }
                                else
                                {
                                    adminNewsFeedView.onShowMessage("Fail to update");
                                    dialog.dismiss();
                                    adminNewsFeedView.onHideProgressBar();

                                }

                            }
                        });

                    } else {
                        adminNewsFeedView.onShowMessage("Please fill the fileds");
                    }

                }
            });
        }
    }

}
