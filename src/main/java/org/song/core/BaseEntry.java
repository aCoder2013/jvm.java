package org.song.core;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by song on 16/9/4.
 */
public abstract class BaseEntry implements Entry {

    protected static final byte[] EMPTY_CLASS = new byte[0];

    public static final String PATH_SEPARATOR = ":";

    public static Entry newEntry(String path) {
        if(StringUtils.isEmpty(path)){
            throw new NullPointerException("path can't be null!");
        }
        if (path.contains(PATH_SEPARATOR)) {
            return new CompositeEntry(path);
        }
        if (path.endsWith("*")) {
            return new CompositeEntry(path);
        }
        if (path.endsWith(".jar") || path.endsWith(".JAR")
                || path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }

}
