package info3.gm.com.memoryleaksviewer.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hz7d7v on 7/12/17.
 */

public class FilesFetcher {

    private static final String TAG = "FilesFetcher";

    public static List<File> listFiles(String leakDirPath, Context context)
    {
        List<File> files = new ArrayList<>();
        String path = context.getExternalFilesDir(leakDirPath).getPath();
        Log.d(TAG, context.getExternalFilesDir(leakDirPath).getPath());

        File directory = new File(path);
        File[] externalFiles = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".result");
            }
        });

        if(externalFiles!=null) {
            files.addAll(Arrays.asList(externalFiles));
        }
        return files;
    }
}
