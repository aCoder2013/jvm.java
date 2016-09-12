package org.song.core;

import org.song.helper.GlobalHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 16/9/11.
 */
public class CompositeEntry extends BaseEntry {

    private List<Entry> entryList = new ArrayList<>();

    public CompositeEntry(String pathList) {
        String[] paths = pathList.split(PATH_SEPARATOR);
        for (String path : paths) {
            Entry entry = newEntry(path);
            if (entry != null) {
                entryList.add(entry);
            } else {
                System.out.println("Ignore empty entry,please check!");
            }
        }
    }


    @Override
    public byte[] readClass(String className) throws Exception {
        return GlobalHelper.readClassFromEntryList(entryList, className);
    }
}
