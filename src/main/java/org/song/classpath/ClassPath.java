package org.song.classpath;

import org.apache.commons.lang3.StringUtils;
import org.song.core.entry.BaseEntry;
import org.song.core.entry.Entry;
import org.song.core.entry.WildcardEntry;
import org.song.helper.AssertUtils;

/**
 * Created by song on 16/9/6.
 */
public class ClassPath {

    private Entry bootClasspath;

    private Entry extClasspath;

    private Entry userClassPath;

    public ClassPath() {
        this(null);
    }

    public ClassPath(String cpOption) {
        init(cpOption);
    }

    private void init(String cpOption) {
        initBootAndExtClassPath();
        initUserClassPath(cpOption);
    }

    private void initUserClassPath(String cpOption) {
        if (StringUtils.isNotEmpty(cpOption)) {
            userClassPath = BaseEntry.newEntry(cpOption);
        }
    }


    private void initBootAndExtClassPath() {
        String javaHome = System.getenv("JAVA_HOME");
        AssertUtils.notEmpty(javaHome, "Please set JAVA_HOME");
        // jre/lib/*
        String bootClassPathString = javaHome + "/jre/lib/*";
        bootClasspath = new WildcardEntry(bootClassPathString);

        // jre/lib/ext/*
        String extClassPathString = javaHome + "/jre/lib/ext/*";
        extClasspath = new WildcardEntry(extClassPathString);
    }


    public byte[] readClass(String className) throws Exception {
        byte[] bytes = bootClasspath.readClass(className);
        if (bytes != null && bytes.length > 0) {
            return bytes;
        }
        bytes = extClasspath.readClass(className);
        if (bytes != null && bytes.length > 0) {
            return bytes;
        }
        bytes = userClassPath.readClass(className);
        if (bytes != null && bytes.length > 0) {
            return bytes;
        }
        return null;
    }

}
