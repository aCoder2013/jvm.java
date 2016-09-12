package org.song.classpath;

import org.song.core.Entry;
import org.song.core.WildcardEntry;
import org.song.helper.AssertUtils;

/**
 * Created by song on 16/9/6.
 */
public class ClassPath {

    private Entry bootClasspath;

    private Entry extClasspath;

    private Entry userClassPath;

    public ClassPath() {
        init();
    }

    private void init() {
        initBootAndExtClassPath();
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
        if (bytes != null) {
            return bytes;
        }
        return null;
    }

}
