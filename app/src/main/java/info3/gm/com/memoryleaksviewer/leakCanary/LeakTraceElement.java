package info3.gm.com.memoryleaksviewer.leakCanary;

/**
 * Created by hz7d7v on 7/10/17.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static info3.gm.com.memoryleaksviewer.leakCanary.LeakTraceElement.Holder.ARRAY;
import static info3.gm.com.memoryleaksviewer.leakCanary.LeakTraceElement.Holder.CLASS;
import static info3.gm.com.memoryleaksviewer.leakCanary.LeakTraceElement.Holder.THREAD;
import static info3.gm.com.memoryleaksviewer.leakCanary.LeakTraceElement.Type.STATIC_FIELD;
import static java.util.Collections.unmodifiableList;
import static java.util.Locale.US;

/** Represents one reference in the chain of references that holds a leaking object in memory. */
public final class LeakTraceElement implements Serializable {
    public enum Type {
        INSTANCE_FIELD, STATIC_FIELD, LOCAL, ARRAY_ENTRY
    }

    public enum Holder {
        OBJECT, CLASS, THREAD, ARRAY
    }

    /** Null if this is the last element in the leak trace, ie the leaking object. */
    public final String referenceName;

    /** Null if this is the last element in the leak trace, ie the leaking object. */
    public final Type type;
    public final Holder holder;
    public final String className;

    /** Additional information, may be null. */
    public final String extra;

    /** If not null, there was no path that could exclude this element. */
    public final Exclusion exclusion;

    /** List of all fields (member and static) for that object. */
    public final List<String> fields;

    LeakTraceElement(String referenceName, Type type, Holder holder, String className, String extra,
                     Exclusion exclusion, List<String> fields) {
        this.referenceName = referenceName;
        this.type = type;
        this.holder = holder;
        this.className = className;
        this.extra = extra;
        this.exclusion = exclusion;
        this.fields = unmodifiableList(new ArrayList<>(fields));
    }

    @Override public String toString() {
        String string = "";

        if (type == STATIC_FIELD) {
            string += "static ";
        }

        if (holder == ARRAY || holder == THREAD) {
            string += holder.name().toLowerCase(US) + " ";
        }

        string += className;

        if (referenceName != null) {
            string += "." + referenceName;
        } else {
            string += " instance";
        }

        if (extra != null) {
            string += " " + extra;
        }

        if (exclusion != null) {
            string += " , matching exclusion " + exclusion.matching;
        }

        return string;
    }

    public String toDetailedString() {
        String string = "* ";
        if (holder == ARRAY) {
            string += "Array of";
        } else if (holder == CLASS) {
            string += "Class";
        } else {
            string += "Instance of";
        }
        string += " " + className + "\n";
        for (String field : fields) {
            string += "|   " + field + "\n";
        }
        return string;
    }
}
