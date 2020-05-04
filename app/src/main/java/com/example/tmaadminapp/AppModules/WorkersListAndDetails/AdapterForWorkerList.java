package com.example.tmaadminapp.AppModules.WorkersListAndDetails;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterForWorkerList extends RecyclerView.Adapter<AdapterForWorkerList.MyWorkerViewHolder>
{
    private List<ModelForWorkerList> workersList;
    private Context ctx;

    public AdapterForWorkerList(List<ModelForWorkerList> workersList, Context ctx) {
        this.workersList = workersList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyWorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(ctx).inflate(R.layout.design_for_work_list_recycler , null);
        MyWorkerViewHolder holder = new MyWorkerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyWorkerViewHolder holder, int position)
    {
        final ModelForWorkerList model = workersList.get(position);

        holder.setWorkerName(model.getNameOfWorker());
        holder.setRatingBar(Float.parseFloat(model.getAverage_rating()));
        holder.setGradingOfWorker(Float.parseFloat(model.getAverage_rating()));

        holder.moreIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Worker List");
                final String refKey = model.getPushKey();

                //creating a popup menu
                PopupMenu popup = new PopupMenu(ctx, holder.moreIconBtn);
                //inflating menu from xml resource
                popup.inflate(R.menu.option_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.detail:

                                holder.checkWorkerDetails(model.getPushKey() , model.getNameOfWorker() , model.getAverage_rating());

                                break;

                            case R.id.delete:

                                holder.deleteWorker(dbRef , refKey);


                                break;

                            case R.id.edit:

                                holder.editWorker(dbRef , refKey);

                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();



            }
        });

    }

    @Override
    public int getItemCount() {
        return workersList.size();
    }

    // worker view holder class
    public class MyWorkerViewHolder extends RecyclerView.ViewHolder {

        private TextView workerName , gradingOfWorker , workerReviews;
        private RatingBar ratingBar;
        private ImageButton moreIconBtn;
        private View mView;

        public MyWorkerViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            moreIconBtn   = mView.findViewById(R.id.moreIconButton);
        }

        private void setWorkerName(String name)
        {
            workerName = mView.findViewById(R.id.workerNameInItemDesign);
            workerName.setText(name);
        }

        private void setGradingOfWorker(float rating)
        {
            gradingOfWorker  = mView.findViewById(R.id.ratingResultInItemDesign);

            if(rating > 1 && rating <=2.5)
            {
                gradingOfWorker.setText("Not Satisfied");
            }
           else if(rating > 2.5 && rating <=3.5)
            {
                gradingOfWorker.setText("Average");
            }
           else if(rating > 3.5 && rating <=4.5)
            {
                gradingOfWorker.setText("Excellent");
            }
           else if(rating > 4.5)
            {
                gradingOfWorker.setText("Outstanding");
            }


        }

        private void setRatingBar (float workerRating)
        {
            ratingBar  = mView.findViewById(R.id.workerRatingInItemDesign);
            ratingBar.setRating((float) workerRating);
        }



        // move to worker details activity
        public void checkWorkerDetails(String workerKey, String workerName, String averageRating)
        {
            Intent detailActivity = new Intent(ctx , WorkerDetailActivity.class);
            detailActivity.putExtra("name", workerName);
            detailActivity.putExtra("average_rating", averageRating);
            detailActivity.putExtra("worker_key", workerKey);
            ctx.startActivity(detailActivity);
        }

        // edit worker name and phnoe dialog
        public void editWorker(final DatabaseReference dbRef , final String refKey)
        {
            // inflate edit worker layout
            View myView = LayoutInflater.from(ctx).inflate(R.layout.edit_worker_layout, null);
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ctx);
            alertBuilder.setView(myView);

            final AlertDialog dialog = alertBuilder.create();
            dialog.setCanceledOnTouchOutside(false);

            Button btn = myView.findViewById(R.id.update_worker_btn_in_update_layout);
            final EditText name , phone;
            name  = myView.findViewById(R.id.worker_name_in_update_layout);
            phone = myView.findViewById(R.id.worker_phone_no_in_update_layout);


            // click on update button in update layout
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String workerName , workerPhone;
                    workerName = name.getText().toString();
                    workerPhone = phone.getText().toString();

                    if(!workerName.isEmpty() && !workerPhone.isEmpty())
                    {

                        Map updateWorker = new HashMap();
                        updateWorker.put("nameOfWorker" , workerName);
                        updateWorker.put("phone" , workerPhone);

                        // update the worker name and phone
                        dbRef.child(refKey).updateChildren(updateWorker).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                dialog.cancel();
                                Toast.makeText(ctx, "Successfully updated", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                    else
                    {
                        Toast.makeText(ctx, "All fields are require", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            dialog.show();
        }

        // delete worker
        public void deleteWorker(final DatabaseReference dbRef, final String refKey)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(mView.getRootView().getContext());
            alert.setMessage("Do you want to delete worker?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i)
                {
                    dbRef.orderByChild("pushKey").equalTo(refKey).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            for(DataSnapshot dataSnap : dataSnapshot.getChildren())
                            {
                                dataSnap.getRef().removeValue();
                                dialogInterface.dismiss();
                                workersList.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getAdapterPosition(), workersList.size());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();
                }
            });

            alert.show();

        }
    }
}
