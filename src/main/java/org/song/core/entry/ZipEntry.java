package org.song.core.entry;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;

/**
 * Created by song on 16/9/4.
 */
public class ZipEntry extends BaseEntry {

    private String absolutePath;

    private ZipFile zipFile = null;

    private static Map<String, byte[]> entryCache = new HashMap<>();

    public ZipEntry(String absolutePath) {
        this.absolutePath = absolutePath;
        zipFile = null;
        try {
            zipFile = new ZipFile(this.absolutePath);
            parseZipFile(zipFile);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseZipFile(ZipFile zipFile) throws IOException {
        Enumeration<? extends java.util.zip.ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            java.util.zip.ZipEntry entry = entries.nextElement();
            String entryName = entry.getName();
            if (entry.isDirectory() || !entryName.endsWith(".class")) {
                continue;
            } else {
                String fullClassName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                InputStream inputStream = zipFile.getInputStream(entry);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                entryCache.put(fullClassName, bytes);
            }
        }
    }

    public byte[] readClass(String className) throws Exception {
        if (entryCache.containsKey(className)) {
            byte[] bytes = entryCache.get(className);
            return bytes;
        }
        return EMPTY_CLASS;
    }
}
