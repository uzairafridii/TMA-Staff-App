package com.example.tmaadminapp.AppModules.SanitationHead.SanitationComplaints;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tmaadminapp.AppModules.SanitationHead.ComplaintFragements.CompletedComplaints.CompletedComplaints;
import com.example.tmaadminapp.AppModules.SanitationHead.ComplaintFragements.PendingComplaints.PendingComplaints;

public class ViewPagerAdapter extends FragmentPagerAdapter
{

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new PendingComplaints();
        }
        else if (position == 1)
        {
            fragment = new CompletedComplaints();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Pending Complaints";
        }
        else if (position == 1)
        {
            title = "Completed Complaints";
        }

        return title;
    }

}
