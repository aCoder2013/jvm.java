package org.song.core.entry;

import org.song.helper.GlobalHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 16/9/11.
 */
public class WildcardEntry extends BaseEntry {

    private List<Entry> entryList = new ArrayList<>();

    public WildcardEntry(String wildcardPath) {
        wildcardPath = wildcardPath.substring(0, wildcardPath.length() - 1);//remove *
        File file = new File(wildcardPath);
        if (!file.exists() || !file.canRead()) {
            throw new RuntimeException(wildcardPath + " doesn't exist or can't be read");
        }
        File[] files = file.listFiles();
        for (File f : files) {
            String absolutePath = f.getAbsolutePath();
            if (absolutePath.endsWith(".jar") || absolutePath.endsWith(".JAR")) {
                Entry entry = newEntry(absolutePath);
                if (entry != null) {
                    entryList.add(entry);
                } else {
                    System.out.println("Ignore null entry,please check!");
                }
            }
        }
    }

    @Override
    public byte[] readClass(String className) throws Exception {
        return GlobalHelper.readClassFromEntryList(entryList, className);
    }
}
