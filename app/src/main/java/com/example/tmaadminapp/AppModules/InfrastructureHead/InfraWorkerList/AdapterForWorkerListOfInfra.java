package com.example.tmaadminapp.AppModules.InfrastructureHead.InfraWorkerList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationWorkers.ModelForWorkerList;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationWorkers.WorkerDetailActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterForWorkerListOfInfra extends RecyclerView.Adapter<AdapterForWorkerListOfInfra.MyWorkerListViewHodler>
{

    private ArrayList<ModelForWorkerList> modelForWorkerLists;
    private Context ctx;


    public AdapterForWorkerListOfInfra(ArrayList<ModelForWorkerList> modelForWorkerLists, Context ctx) {
        this.modelForWorkerLists = modelForWorkerLists;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyWorkerListViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.design_for_work_list_recycler , null);
        MyWorkerListViewHodler holder  = new MyWorkerListViewHodler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyWorkerListViewHodler holder, int position)
    {
        ModelForWorkerList model = modelForWorkerLists.get(position);

        holder.setWorkerName(model.getNameOfWorker());
        holder.setRatingBar(model.getRating());
        holder.setGradingOfWorker(model.getRating());

        holder.moreIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
                                Intent detailActivity = new Intent(ctx , WorkerDetailActivity.class);
                                ctx.startActivity(detailActivity);
                                break;

                            case R.id.delete:
                                //handle menu2 click
                                break;

                            case R.id.edit:
                                // inflate edit worker layout
                                View myView = LayoutInflater.from(ctx).inflate(R.layout.edit_worker_layout, null);
                                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ctx);
                                alertBuilder.setView(myView);

                                final AlertDialog dialog = alertBuilder.create();
                                dialog.setCanceledOnTouchOutside(false);
                                dialog.setCancelable(false);

                                Button btn = myView.findViewById(R.id.update_worker_btn_in_update_layout);
                                // click on update button in update layout
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        dialog.cancel();
                                    }
                                });
                                dialog.show();

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
        return modelForWorkerLists.size();
    }

    public class MyWorkerListViewHodler extends RecyclerView.ViewHolder {

        private TextView workerName , gradingOfWorker;
        private CircleImageView profileImage;
        private RatingBar ratingBar;
        private ImageButton moreIconBtn;
        private View mView;

        public MyWorkerListViewHodler(@NonNull View itemView) {
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
            ratingBar.setRating(workerRating);
        }

    }
}
