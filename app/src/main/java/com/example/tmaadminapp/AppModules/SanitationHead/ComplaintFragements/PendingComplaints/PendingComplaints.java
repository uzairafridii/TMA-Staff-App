package com.example.tmaadminapp.AppModules.SanitationHead.ComplaintFragements.PendingComplaints;


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
public class PendingComplaints extends Fragment {

    private View pComplaintsView;
    private RecyclerView listOfPendingComplaints;
    private AdapterForPComplaintRecycler adapter;
    private ArrayList<ModelForPendingComplaints> arrayList;
    public PendingComplaints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        pComplaintsView = inflater.inflate(R.layout.fragment_pending_complaints, container, false);

        initViews();
        setItemsInRecyclerView();
        listOfPendingComplaints.setAdapter(adapter);
        return pComplaintsView;
    }

    private void initViews()
    {
       listOfPendingComplaints = pComplaintsView.findViewById(R.id.pendingComplaintsListOfSanitation);
       listOfPendingComplaints.setLayoutManager(new LinearLayoutManager(getContext()));

       arrayList  = new ArrayList<>();

       adapter = new AdapterForPComplaintRecycler(arrayList , getContext());

    }

    private void setItemsInRecyclerView()
    {
        String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        arrayList.add(new ModelForPendingComplaints("First", getString(R.string.dummy_text),
                        "Pending", date ));
        arrayList.add(new ModelForPendingComplaints("Second", getString(R.string.dummy_text),
                        "Pending", date ));

        arrayList.add(new ModelForPendingComplaints("Third", getString(R.string.dummy_text),
                "Pending", date ));
        arrayList.add(new ModelForPendingComplaints("Fouth", getString(R.string.dummy_text),
                "Pending", date ));
    }

}
