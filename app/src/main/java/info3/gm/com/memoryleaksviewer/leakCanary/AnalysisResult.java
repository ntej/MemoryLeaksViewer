package info3.gm.com.memoryleaksviewer.leakCanary;

import java.io.Serializable;

/**
 * Created by hz7d7v on 7/10/17.
 */

public class AnalysisResult implements Serializable {

    public static AnalysisResult noLeak(long analysisDurationMs) {
        return new AnalysisResult(false, false, null, null, null, 0, analysisDurationMs);
    }

    public static AnalysisResult leakDetected(boolean excludedLeak, String className,
                                              LeakTrace leakTrace, long retainedHeapSize, long analysisDurationMs) {
        return new AnalysisResult(true, excludedLeak, className, leakTrace, null, retainedHeapSize,
                analysisDurationMs);
    }

    public static AnalysisResult failure(Throwable failure, long analysisDurationMs) {
        return new AnalysisResult(false, false, null, null, failure, 0, analysisDurationMs);
    }

    /** True if a leak was found in the heap dump. */
    public final boolean leakFound;

    /**
     * True if {@link #leakFound} is true and the only path to the leaking reference is
     * through excluded references. Usually, that means you can safely ignore this report.
     */
    public final boolean excludedLeak;

    /**
     * Class name of the object that leaked if {@link #leakFound} is true, null otherwise.
     * The class name format is the same as what would be returned by {@link Class#getName()}.
     */
    public final String className;

    /**
     * Shortest path to GC roots for the leaking object if {@link #leakFound} is true, null
     * otherwise. This can be used as a unique signature for the leak.
     */
    public final LeakTrace leakTrace;

    /** Null unless the analysis failed. */
    public final Throwable failure;

    /**
     * The number of bytes which would be freed if all references to the leaking object were
     * released. 0 if {@link #leakFound} is false.
     */
    public final long retainedHeapSize;

    /** Total time spent analyzing the heap. */
    public final long analysisDurationMs;

    private AnalysisResult(boolean leakFound, boolean excludedLeak, String className,
                           LeakTrace leakTrace, Throwable failure, long retainedHeapSize, long analysisDurationMs) {
        this.leakFound = leakFound;
        this.excludedLeak = excludedLeak;
        this.className = className;
        this.leakTrace = leakTrace;
        this.failure = failure;
        this.retainedHeapSize = retainedHeapSize;
        this.analysisDurationMs = analysisDurationMs;
    }
}
