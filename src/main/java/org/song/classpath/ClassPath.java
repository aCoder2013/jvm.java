package org.song.classpath;

import org.song.core.Entry;
import org.song.core.WildcardEntry;
import org.song.helper.AssertUtils;

import java.nio.ByteBuffer;

/**
 * Created by song on 16/9/6.
 */
public class ClassPath {

    private Entry bootClasspath;

    private Entry extClasspath;

    private Entry userClassPath;

    public ClassPath() {
        String javaHome = System.getenv("JAVA_HOME");
        AssertUtils.notEmpty(javaHome, "Please set JAVA_HOME");
        String bootClassPathString = javaHome + "/jre/lib/*";
        bootClasspath = new WildcardEntry(bootClassPathString);
    }


    public byte[] readClass(String className) throws Exception {
        byte[] bytes = bootClasspath.readClass(className);
        if (bytes != null) {
            return bytes;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        ClassPath classPath = new ClassPath();
        byte[] bytes = classPath.readClass("java.lang.Object");
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        System.out.println(Integer.toHexString(byteBuffer.getInt()).toUpperCase());
    }
}
