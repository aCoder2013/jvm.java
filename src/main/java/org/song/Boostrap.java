package org.song;

import org.song.cmd.CmdParser;
import org.song.cmd.support.DefaultCmdParser;

/**
 * Created by song on 16/9/4.
 */
public class Boostrap {


    public static void main(String[] args) throws Exception {
        CmdParser cmdParser = new DefaultCmdParser();
        cmdParser.parse(args);
    }
}
