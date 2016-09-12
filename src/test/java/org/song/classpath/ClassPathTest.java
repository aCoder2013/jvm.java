package org.song.classpath;

import org.junit.Test;

/**
 * Created by song on 16/9/12.
 */
public class ClassPathTest {

    @Test
    public void extClassPath() throws Exception {
        ClassPath classPath = new ClassPath();
        byte[] bytes = classPath.readClass("sun.security.ec.SunEC");
        System.out.println(bytes);
        System.out.println(bytes.length);
    }
}
