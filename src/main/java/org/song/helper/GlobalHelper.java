package org.song.helper;

import org.apache.commons.cli.CommandLine;
import org.song.cmd.CmdParser;
import org.song.cmd.JVMOption;
import org.song.core.Entry;

import java.util.List;

/**
 * Created by song on 16/9/4.
 */
public class GlobalHelper {

    public JVMOption toJvmOption(CommandLine line) {
        if (line != null) {
            if (line.hasOption(CmdParser.CLASS_PATH)) {
                String classPath = line.getOptionValue(CmdParser.CLASS_PATH);

            }
        }
        return null;
    }


    public static byte[] readClassFromEntryList(List<Entry> entryList, String className) {
        if (entryList.size() > 0) {
            for (Entry entry : entryList) {
                byte[] bytes = new byte[0];
                try {
                    bytes = entry.readClass(className);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (bytes != null && bytes.length > 0) {
                    return bytes;
                }
            }
        }
        return new byte[0];
    }
}
