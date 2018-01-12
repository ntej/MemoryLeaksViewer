package info3.gm.com.memoryleaksviewer.leakCanary;

import java.io.File;
import java.io.Serializable;

import static info3.gm.com.memoryleaksviewer.leakCanary.Preconditions.checkNotNull;

/**
 * Created by hz7d7v on 7/10/17.
 */

/** Data structure holding information about a heap dump. */
public class HeapDump implements Serializable {

    /** Receives a heap dump to analyze. */
    public interface Listener {
        Listener NONE = new Listener() {
            @Override public void analyze(HeapDump heapDump, String passedObjectSimpleName) {
            }
        };

        void analyze(HeapDump heapDump, String passedObjectSimpleName);
    }

    /** The heap dump file, which you might want to upload somewhere. */
    public final File heapDumpFile;

    /**
     * Key associated to the {@link KeyedWeakReference} used to detect the memory leak.
     * When analyzing a heap dump, search for all {@link KeyedWeakReference} instances, then open
     * the one that has its "key" field set to this value. Its "referent" field contains the
     * leaking object. Computing the shortest path to GC roots on that leaking object should enable
     * you to figure out the cause of the leak.
     */
    public final String referenceKey;

    /**
     * User defined name to help identify the leaking instance.
     */
    public final String referenceName;

    /** References that should be ignored when analyzing this heap dump. */
    public final ExcludedRefs excludedRefs;

    /** Time from the request to watch the reference until the GC was triggered. */
    public final long watchDurationMs;
    public final long gcDurationMs;
    public final long heapDumpDurationMs;

    public HeapDump(File heapDumpFile, String referenceKey, String referenceName,
                    ExcludedRefs excludedRefs, long watchDurationMs, long gcDurationMs, long heapDumpDurationMs) {
        this.heapDumpFile = checkNotNull(heapDumpFile, "heapDumpFile");
        this.referenceKey = checkNotNull(referenceKey, "referenceKey");
        this.referenceName = checkNotNull(referenceName, "referenceName");
        this.excludedRefs = checkNotNull(excludedRefs, "excludedRefs");
        this.watchDurationMs = watchDurationMs;
        this.gcDurationMs = gcDurationMs;
        this.heapDumpDurationMs = heapDumpDurationMs;
    }

}
