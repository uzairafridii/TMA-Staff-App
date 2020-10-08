package com.example.tmaadminapp.AppModules.Regulation.Activities.Bills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.BillView;

import java.util.List;

public class BillsRecyclerAdapter extends RecyclerView.Adapter<BillsRecyclerAdapter.MyBillsViewHolder>
{

    private List<BillsModel> billList;
    private Context context;
    private BillView billView;

    public BillsRecyclerAdapter(List<BillsModel> billList, Context context, BillView view) {
        this.billList = billList;
        this.context = context;
        this.billView = view;
    }

    @NonNull
    @Override
    public MyBillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(context).inflate(R.layout.design_for_bill_recycler, null);
        return new MyBillsViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyBillsViewHolder holder, int position)
    {
        final BillsModel model = billList.get(position);
        holder.name.setText(model.getOwnerName());
        holder.address.setText(model.getConnectionAddress());
        holder.date.setText("Month "+model.getBillMonth());

        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final PopupMenu menu  = new PopupMenu(context, holder.menuBtn);
                menu.inflate(R.menu.bill_menu);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {


                        switch (menuItem.getItemId())
                        {
                            case R.id.uploadBill:
                            {
                                billView.getRefNo(model.getRefNo());
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
    public int getItemCount()
    {
        if(billList.size() > 0)
        {
            billView.hideLayout();
            return billList.size();
        }
        else {
            billView.showLayout();
            return billList.size();
        }
    }

    public class MyBillsViewHolder extends RecyclerView.ViewHolder
    {

        private TextView name, address, date;
        private ImageButton menuBtn;
        private View mView;

        public MyBillsViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            name = itemView.findViewById(R.id.ownerNameInRV);
            address = itemView.findViewById(R.id.address);
            date = itemView.findViewById(R.id.billLastUpdate);
            menuBtn = itemView.findViewById(R.id.moreIconButtonInBill);


        }



    }
}
