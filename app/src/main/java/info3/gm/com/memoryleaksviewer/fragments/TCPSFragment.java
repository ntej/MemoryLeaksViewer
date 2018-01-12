package info3.gm.com.memoryleaksviewer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import info3.gm.com.memoryleaksviewer.R;
import info3.gm.com.memoryleaksviewer.leakCanary.AnalysisResult;
import info3.gm.com.memoryleaksviewer.leakCanary.HeapDump;
import info3.gm.com.memoryleaksviewer.utils.FilesFetcher;


/**
 * A simple {@link Fragment} subclass.
 */
public class TCPSFragment extends Fragment {


    private static final String TCPS_LEAKS_RESULT_PATH = "lc/tcps/";
    private static final String TAG = "TCPSFragment";
    private List<File> resultFiles = new ArrayList<>();


    // null until it's been first loaded.
    List<Leak> leaks;

    private ListView listView;
    private TextView failureView;
    private Button actionButton;

    public TCPSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        resultFiles = FilesFetcher.listFiles(TCPS_LEAKS_RESULT_PATH,getActivity().getApplicationContext());


        listView = (ListView) getView().findViewById(R.id.leak_canary_display_leak_list);
        failureView = (TextView) getView().findViewById(R.id.leak_canary_display_leak_failure);
        actionButton = (Button) getView().findViewById(R.id.leak_canary_action);

        return inflater.inflate(R.layout.fragment_tcps, container, false);
    }


    static class Leak {
        final HeapDump heapDump;
        final AnalysisResult result;
        final File resultFile;

        Leak(HeapDump heapDump, AnalysisResult result, File resultFile) {
            this.heapDump = heapDump;
            this.result = result;
            this.resultFile = resultFile;
        }
    }

}
