package com.example.tmaadminapp.AppModules.UnionCouncilHead;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.CertificatesView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterForCertificatesRecycler extends RecyclerView.Adapter<AdapterForCertificatesRecycler.MyCertificateViewHolder>
{
   private Context context;
   private List<CertificatesModel> modelList;
   private CertificatesView view;

    public AdapterForCertificatesRecycler(Context context, List<CertificatesModel> modelList, CertificatesView view) {
        this.context = context;
        this.modelList = modelList;
        this.view = view;
    }

    @NonNull
    @Override
    public MyCertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.certificate_recycler_design , null);
        return new MyCertificateViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCertificateViewHolder holder, final int position)
    {
        final CertificatesModel model = modelList.get(position);
        holder.applicantName.setText(model.getApplicantName());
        holder.certificateType.setText(model.getCertificateType());
        holder.date.setText(model.getDate());
        holder.setStatus(model.getStatus());
        holder.unionCouncil.setText(model.getUnionCouncil());

        holder.moreImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Certificates");
                PopupMenu popupMenu = new PopupMenu(context , holder.moreImageBtn);
                popupMenu.inflate(R.menu.certificate_option_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.certificateDetailsMenu:
                            {
                                Intent detailsIntent = new Intent(context, CertificatesDetailsActivity.class);
                                detailsIntent.putExtra("refKey" , model.getPushKey());
                                context.startActivity(detailsIntent);
                                break;
                            }

                            case R.id.markAsComplete:
                            {
                                if(model.getStatus().equals("Completed"))
                                {
                                    Toast.makeText(context, "Already completed", Toast.LENGTH_SHORT).show();
                                }else {
                                    holder.markAsCompleted(dbRef, model.getPushKey());

                                }
                                break;
                            }
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        if(modelList.size() > 0) {
            view.hideTextView();
            return modelList.size();
        }
        else
        {
            return modelList.size();
        }
    }

    public class MyCertificateViewHolder extends RecyclerView.ViewHolder
    {
        private TextView applicantName , certificateType , unionCouncil , date , status;
        private ImageButton moreImageBtn;
        private View mView;

        public MyCertificateViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            applicantName = mView.findViewById(R.id.applicantName);
            certificateType = mView.findViewById(R.id.certificateTypeValue);
            unionCouncil = mView.findViewById(R.id.ucValues);
            date = mView.findViewById(R.id.dateInCertificate);

            moreImageBtn = mView.findViewById(R.id.moreButtonInCertificate);
        }

        private void setStatus(String certificateStatus)
        {
            status = mView.findViewById(R.id.certificateStatus);

            if(certificateStatus.equals("Pending"))
            {
                status.setText(certificateStatus);
                status.setTextColor(Color.RED);
            }
            else
            {
                status.setText(certificateStatus);
                status.setTextColor(Color.BLUE);
            }
        }


        // method to change status
        public void markAsCompleted(final DatabaseReference dbRef, final String pushKey)
        {
            final AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage("Mark as completed ?");
            alert.setTitle("Completion");
            alert.setCancelable(false);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i)
                {
                    Map<String, Object> updateStatus = new HashMap<>();
                    updateStatus.put("status", "Completed");
                    dbRef.child(pushKey).updateChildren(updateStatus).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(context, "Mark as completed", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                            else
                            {
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }

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
