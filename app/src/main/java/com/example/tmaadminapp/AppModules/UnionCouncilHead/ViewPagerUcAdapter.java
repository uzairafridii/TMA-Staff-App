package com.example.tmaadminapp.AppModules.UnionCouncilHead;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tmaadminapp.AppModules.UnionCouncilHead.UcFragment.CompletedCertificates;
import com.example.tmaadminapp.AppModules.UnionCouncilHead.UcFragment.PendingCertificates;

public class ViewPagerUcAdapter  extends FragmentPagerAdapter
{

    public ViewPagerUcAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new PendingCertificates();
        }
        else if (position == 1)
        {
            fragment = new CompletedCertificates();
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
            title = "Pending Certificates";
        }
        else if (position == 1)
        {
            title = "Completed Certificates";
        }

        return title;
    }

}
