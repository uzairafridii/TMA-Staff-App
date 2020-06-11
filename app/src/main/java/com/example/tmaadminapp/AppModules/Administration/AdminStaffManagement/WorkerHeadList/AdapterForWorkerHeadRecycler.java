package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.WorkerHeadView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterForWorkerHeadRecycler extends RecyclerView.Adapter<AdapterForWorkerHeadRecycler.MyHeadViewHolder>
{
    private List<ModelForWorkerHead> arrayList;
    private Context context;
    private DatabaseReference databaseReference;
    private WorkerHeadView workerHeadView;

    public AdapterForWorkerHeadRecycler(List<ModelForWorkerHead> arrayList, Context context,
                                        DatabaseReference databaseReference, WorkerHeadView workerHeadView)
    {
        this.arrayList = arrayList;
        this.context = context;
        this.databaseReference = databaseReference;
        this.workerHeadView = workerHeadView;
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
        final ModelForWorkerHead  model = arrayList.get(position);
        holder.setNameOfWorkerHead(model.getName_worker_head());
        holder.setFieldOfWorkerHead(model.getDepartment());

        // popup menu button click
        holder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // show popup menu's
                PopupMenu menu = new PopupMenu(context , holder.menuButton);
                menu.inflate(R.menu.option_menu);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.edit:
                            {
                                holder.updateValueDialog(model.getUid());
                                break;
                            }

                            case R.id.delete:
                            {
                                holder.deleteCurrentValue(model.getUid());
                                break;
                            }

                            case R.id.detail:
                            {
                                new BottomSheetWorkerHeadDetails(model.getUid()).show(((FragmentActivity)context)
                                        .getSupportFragmentManager(), "detail bottom sheet");
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



        // delete value method
        private void deleteCurrentValue(final String key)
        {

            Log.d("userKey", "deleteCurrentValue: "+key);
            AlertDialog.Builder alert = new AlertDialog.Builder(mView.getRootView().getContext());
            alert.setMessage("Do you want to delete worker head?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i)
                {

                    Query query = databaseReference.child("WorkersHead").orderByChild("uid").equalTo(key);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            // remove current item
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                data.getRef().removeValue();

                                Log.d("refKeyChecking", "onDataChange: "+data.getRef());

                                dialogInterface.dismiss();
                                arrayList.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
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
        private void updateValueDialog(final String uid) {
            View myView = LayoutInflater.from(context).inflate(R.layout.workers_head_sign_up, null);
            AlertDialog.Builder alert = new AlertDialog.Builder(mView.getRootView().getContext());
            alert.setView(myView);

            final AlertDialog dialog = alert.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            // change button and text view text
            TextView title = myView.findViewById(R.id.signUpText);
            title.setText("Upate Worker Head");

            Button button = myView.findViewById(R.id.registerWorkerHeadButton);
            button.setText("Update");
            button.setTextSize(14);

            // initialize edittext fields
            final EditText name, phone , email , password , department;
            name = myView.findViewById(R.id.worker_head_name);
            phone = myView.findViewById(R.id.worker_head_phone_no);
            department = myView.findViewById(R.id.worker_head_dept);

            email = myView.findViewById(R.id.worker_head_email);
            email.setVisibility(View.INVISIBLE);
            password = myView.findViewById(R.id.worker_head_password);
            password.setVisibility(View.INVISIBLE);

            // button upate click

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!name.getText().toString().isEmpty() &&
                            !phone.getText().toString().isEmpty() && !department.getText().toString().isEmpty())
                    {
                        workerHeadView.onShowProgressBar();

                        // set data in map
                        HashMap<String, Object> updateData = new HashMap<>();
                        updateData.put("name_worker_head", name.getText().toString());
                        updateData.put("phone", phone.getText().toString());
                        updateData.put("department", department.getText().toString());

                        //store update data in firebase
                        databaseReference.child("WorkersHead").child(uid).updateChildren(updateData)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    workerHeadView.showMessage("Successfully updated");
                                    dialog.dismiss();
                                    workerHeadView.onHideProgressBar();
                                }
                                else
                                {
                                    workerHeadView.showMessage("Fail to update");
                                    dialog.dismiss();
                                    workerHeadView.onHideProgressBar();

                                }

                            }
                        });

                    } else {
                        workerHeadView.showMessage("Please fill the fields");
                    }

                }
            });
        }



    }
}
