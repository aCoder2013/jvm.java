package org.song.core.entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.song.helper.AssertUtils;

import java.io.File;

/**
 * Created by song on 16/9/4.
 */
public class DirEntry extends BaseEntry {

    private String absolutePath;


    public DirEntry(String absolutePath) {
        AssertUtils.notNull(absolutePath);
        this.absolutePath = absolutePath;
    }

    public byte[] readClass(String className) throws Exception {
        if (StringUtils.isEmpty(className)) {
            return EMPTY_CLASS;
        }
        className = className.replace('.','/');
        String path = this.absolutePath + className + ".class";
        File file = new File(path);
        if (!file.exists()) {
            throw new ClassNotFoundException("class not found exception : " + className);
        }
        if (!file.canRead()) {
            throw new RuntimeException(className + "can't be read!");
        }
        byte[] bytes = FileUtils.readFileToByteArray(file);
        return bytes;
    }

}
