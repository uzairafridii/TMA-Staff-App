package com.example.tmaadminapp.AppModules.Regulation.Activities.Tax;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.TaxDetailsView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class TaxDetailsAdapter extends RecyclerView.Adapter<TaxDetailsAdapter.MyTaxViewHolder> {
    private List<TaxModel> list;
    private Context context;
    private TaxDetailsView taxView;

    public TaxDetailsAdapter(List<TaxModel> list, Context context, TaxDetailsView taxView) {
        this.list = list;
        this.context = context;
        this.taxView = taxView;
    }

    @NonNull
    @Override
    public MyTaxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(context).inflate(R.layout.desgin_for_tax_recycler, null);
        return new MyTaxViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyTaxViewHolder holder, int position) {
        final TaxModel model = list.get(position);
        holder.setImageView(model.getImageUrl());
        holder.title.setText(model.getTaxTitle());
        holder.amount.setText(model.getTaxAmount());

        final String postId = model.getPostId();

        // click to edit tax details
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.updateValueDialog(postId);
            }
        });

        // click to delete tax details
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.deleteCurrentValue(postId);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size() > 0) {
            // hide layout not found
            taxView.hideLayout();
            return list.size();
        } else {
            //show layout not found
            taxView.showLayout();
            return list.size();
        }
    }

    public class MyTaxViewHolder extends RecyclerView.ViewHolder {

        private TextView title, amount;
        private ImageView imageView;
        private ImageButton editBtn, deleteBtn;
        private View mView;

        public MyTaxViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            title = mView.findViewById(R.id.titleOfTax);
            amount = mView.findViewById(R.id.amountOfTax);
            editBtn = mView.findViewById(R.id.editTaxBtn);
            deleteBtn = mView.findViewById(R.id.deleteTaxBtn);
        }

        private void setImageView(String imageUrl) {

            imageView = mView.findViewById(R.id.imageOfTax);

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.tma_logo)
                    .into(imageView);

        }


        // delete value method
        private void deleteCurrentValue(final String key) {

            final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("TaxDetails");
            AlertDialog.Builder alert = new AlertDialog.Builder(mView.getRootView().getContext());
            alert.setMessage("Do you want to delete tax details?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i) {


                    Query query = dbRef.orderByChild("postId").equalTo(key);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            // remove current item
                            for (DataSnapshot data : dataSnapshot.getChildren()) {

                                data.getRef().removeValue();
                                dialogInterface.dismiss();

                                //update the list with new list size
                                list.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getAdapterPosition(), list.size());
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

            // inflate dialog for updating value
            View myView = LayoutInflater.from(context).inflate(R.layout.add_tax_details_dialog, null);
            AlertDialog.Builder alert = new AlertDialog.Builder(mView.getRootView().getContext());
            alert.setView(myView);

            final AlertDialog dialog = alert.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            // intiate and change button, textView text
            RelativeLayout layout = myView.findViewById(R.id.imageLayout);
            layout.setVisibility(View.GONE);
            TextView title = myView.findViewById(R.id.taxDetailsText);
            title.setText("Update Tax Detils");

            Button button = myView.findViewById(R.id.addNowTaxBtn);
            button.setText("Update");
            button.setTextSize(14);

            final EditText edTitle, edPrice;
            edTitle = myView.findViewById(R.id.taxTitle);
            edPrice = myView.findViewById(R.id.taxPrice);

            final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("TaxDetails");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!edTitle.getText().toString().isEmpty() && !edPrice.getText().toString().isEmpty()) {

                        HashMap<String, Object> updateData = new HashMap<>();
                        updateData.put("taxTitle", edTitle.getText().toString());
                        updateData.put("taxAmount", edPrice.getText().toString());

                        dbRef.child(refKey).updateChildren(updateData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    taxView.message("Successfully updated");
                                    dialog.dismiss();

                                } else {
                                    taxView.message("Fail to update");
                                    dialog.dismiss();
                                }

                            }
                        });

                    } else {
                        taxView.message("Please fill the fileds");
                    }

                }
            });
        }





    }

}
