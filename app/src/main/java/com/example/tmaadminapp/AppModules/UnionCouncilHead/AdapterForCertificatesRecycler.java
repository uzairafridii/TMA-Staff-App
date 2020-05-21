package com.example.tmaadminapp.AppModules.UnionCouncilHead;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.CertificatesView;

import java.util.List;

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
                                detailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                detailsIntent.putExtra("refKey" , model.getPushKey());
                                context.startActivity(detailsIntent);
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
                status.setTextColor(Color.GREEN);
            }
        }
    }
}
