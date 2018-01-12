package info3.gm.com.memoryleaksviewer;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.File;

import info3.gm.com.memoryleaksviewer.adapters.TabPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private String[] leakDirectories ={"accounts","appshop","updater","sm","spn","tcps"};

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createLeakDirectories(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Accounts"));
        tabLayout.addTab(tabLayout.newTab().setText("AppShop"));
        tabLayout.addTab(tabLayout.newTab().setText("AppShopUpdater"));
        tabLayout.addTab(tabLayout.newTab().setText("SM"));
        tabLayout.addTab(tabLayout.newTab().setText("SPN"));
        tabLayout.addTab(tabLayout.newTab().setText("TCPS"));

        final ViewPager viewPager =
                (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter
                (getSupportFragmentManager(),
                        tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener( new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    private void createLeakDirectories(Context context) {

        File filesDir = context.getExternalFilesDir("/lc");

        Log.d(TAG, filesDir.toString());

        for(String dir : leakDirectories)
        {
            try {
                File leakDirectory = new File(filesDir, dir);
                leakDirectory.mkdir();
            }
            catch (Exception e)
            {
                Log.d(TAG, "unable to create the directory");
            }
        }


    }
}
