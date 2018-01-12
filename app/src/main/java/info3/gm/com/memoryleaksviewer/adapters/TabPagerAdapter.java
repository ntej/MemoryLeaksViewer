package info3.gm.com.memoryleaksviewer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info3.gm.com.memoryleaksviewer.fragments.AccountsFragment;
import info3.gm.com.memoryleaksviewer.fragments.AppShopFragment;
import info3.gm.com.memoryleaksviewer.fragments.AppShopUpdaterFragment;
import info3.gm.com.memoryleaksviewer.fragments.SMFragment;
import info3.gm.com.memoryleaksviewer.fragments.SPNFragment;
import info3.gm.com.memoryleaksviewer.fragments.TCPSFragment;

/**
 * Created by hz7d7v on 7/10/17.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs)
    {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new AccountsFragment();
            case 1:
                 return new AppShopFragment();
            case 2:
                return new AppShopUpdaterFragment();
            case 3:
                return new SMFragment();
            case 4:
                return new SPNFragment();
            case 5:
                return new TCPSFragment();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
