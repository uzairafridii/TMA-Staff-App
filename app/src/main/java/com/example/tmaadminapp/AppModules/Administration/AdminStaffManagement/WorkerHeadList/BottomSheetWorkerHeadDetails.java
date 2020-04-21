package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmaadminapp.Models.DetailBottomSheetPresenterImplementer;
import com.example.tmaadminapp.Presenters.DetailBottomSheetPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.DetailBottomSheetView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetWorkerHeadDetails extends BottomSheetDialogFragment implements DetailBottomSheetView
{

    private TextView workerHeadName , workerHeadPhone , workerHeadEmail , dept;
    private View myView;
    private String pushKey;
    private DetailBottomSheetPresenter detailBottomSheetPresenter;
    private DatabaseReference databaseReference;


    public BottomSheetWorkerHeadDetails(String pushKey)
    {
        // Required empty public constructor
        this.pushKey = pushKey;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_bottom_sheet_worker_head_details, container, false);

        initViews();

        detailBottomSheetPresenter.getWorkerDetails(databaseReference , pushKey);

        return myView;
    }

    // initViews
    private void initViews()
    {

        detailBottomSheetPresenter = new DetailBottomSheetPresenterImplementer(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Workers Head");

        workerHeadName = myView.findViewById(R.id.nameInBottomSheet);
        workerHeadPhone = myView.findViewById(R.id.phoneInBottomSheet);
        workerHeadEmail = myView.findViewById(R.id.emailInBottomSheet);
        dept = myView.findViewById(R.id.departmentInBottomSheet);

    }

    // callbacks of details bottom sheet view
    @Override
    public void onSetInTextView(String name, String phone, String email, String department)
    {
        workerHeadName.setText(name);
        workerHeadPhone.setText(phone);
        workerHeadEmail.setText(email);
        dept.setText(department);

    }
}
