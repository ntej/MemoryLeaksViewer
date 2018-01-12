package info3.gm.com.memoryleaksviewer.leakCanary;

import java.io.Serializable;

/**
 * Created by hz7d7v on 7/10/17.
 */

public  final class Exclusion implements Serializable {

    public final String name;
    public final String reason;
    public final boolean alwaysExclude;
    public final String matching;

    Exclusion(ExcludedRefs.ParamsBuilder builder) {
        this.name = builder.name;
        this.reason = builder.reason;
        this.alwaysExclude = builder.alwaysExclude;
        this.matching = builder.matching;
    }

}
