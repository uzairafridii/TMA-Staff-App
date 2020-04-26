package com.example.tmaadminapp.AppModules.InfrastructureHead.ComplaintsFragments.CompletedComplaints;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tmaadminapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfraCompletedComplaints extends Fragment {

    private View completedComplaintViews;
    private RecyclerView listOfCompletedComplaints;


    public InfraCompletedComplaints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        completedComplaintViews = inflater.inflate(R.layout.fragment_infra_completed_complaints, container, false);

        initViews();




        return completedComplaintViews;
    }

    private void initViews()
    {
        listOfCompletedComplaints = completedComplaintViews.findViewById(R.id.completedComplaintsListOfInfra);
        listOfCompletedComplaints.setLayoutManager(new LinearLayoutManager(getContext()));


    }



}
