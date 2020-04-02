package com.example.tmaadminapp.Administration.AdminStaffManagement.WorkerHeadList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;

import java.util.ArrayList;

public class AdapterForWorkerHeadRecycler extends RecyclerView.Adapter<AdapterForWorkerHeadRecycler.MyHeadViewHolder>
{
    private ArrayList<ModelForWorkerHead> arrayList;
    private Context context;

    public AdapterForWorkerHeadRecycler(ArrayList<ModelForWorkerHead> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHeadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.design_for_worker_head_recycler, null);
        MyHeadViewHolder holder = new MyHeadViewHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHeadViewHolder holder, int position)
    {
        ModelForWorkerHead  model = arrayList.get(position);
        holder.setNameOfWorkerHead(model.getNameOfWorkerHead());
        holder.setFieldOfWorkerHead(model.getFieldOfWorkerHead());

        holder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu menu = new PopupMenu(context , holder.menuButton);
                menu.inflate(R.menu.option_menu);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.edit:
                            {
                                Toast.makeText(context, "Edit Menu Clicked", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        return false;
                    }
                });
                menu.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHeadViewHolder extends RecyclerView.ViewHolder
    {
        private View mView;
        private TextView nameOfWorkerHead , fieldOfWorkerHead;
        private ImageButton menuButton;

        public MyHeadViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            menuButton = mView.findViewById(R.id.moreIconButtonInWorkerHead);

        }


        private void setNameOfWorkerHead(String name)
        {
            nameOfWorkerHead = mView.findViewById(R.id.workerHeadNameInItemDesign);
            nameOfWorkerHead.setText(name);
        }

        private void setFieldOfWorkerHead(String field)
        {
            fieldOfWorkerHead = mView.findViewById(R.id.fieldOfWorkerHead);
            fieldOfWorkerHead.setText(field);
        }


    }
}
