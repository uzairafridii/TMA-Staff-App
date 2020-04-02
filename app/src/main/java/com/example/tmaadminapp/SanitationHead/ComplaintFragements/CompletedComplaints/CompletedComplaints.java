package com.example.tmaadminapp.SanitationHead.ComplaintFragements.CompletedComplaints;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.SanitationHead.ComplaintFragements.PendingComplaints.AdapterForPComplaintRecycler;
import com.example.tmaadminapp.SanitationHead.ComplaintFragements.PendingComplaints.ModelForPendingComplaints;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedComplaints extends Fragment {

    private View completedComplaintViews;
    private RecyclerView listOfCompletedComplaints;
    private AdapterForCompletedComplaints adapter;
    private ArrayList<ModelForCompletedComplaints> arrayList;

    public CompletedComplaints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        completedComplaintViews = inflater.inflate(R.layout.fragment_completed_complaints, container, false);

        initViews();

        setItemsInRecyclerView();
        listOfCompletedComplaints.setAdapter(adapter);

        return completedComplaintViews;


    }

    private void initViews()
    {
        listOfCompletedComplaints = completedComplaintViews.findViewById(R.id.completedComplaintsListOfSanitation);
        listOfCompletedComplaints.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayList  = new ArrayList<>();

        adapter = new AdapterForCompletedComplaints(arrayList, getContext());

    }

    private void setItemsInRecyclerView()
    {
        String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        arrayList.add(new ModelForCompletedComplaints("First","Completed",date));
        arrayList.add(new ModelForCompletedComplaints("Second","Completed",date));
        arrayList.add(new ModelForCompletedComplaints("third","Completed",date));
        arrayList.add(new ModelForCompletedComplaints("Fourth","Completed",date));
        arrayList.add(new ModelForCompletedComplaints("Fifth","Completed",date));
        arrayList.add(new ModelForCompletedComplaints("Sixth","Completed",date));
        arrayList.add(new ModelForCompletedComplaints("Seven","Completed",date));

    }

}
