package info3.gm.com.memoryleaksviewer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info3.gm.com.memoryleaksviewer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SPNFragment extends Fragment {




    public SPNFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spn, container, false);
    }

}
