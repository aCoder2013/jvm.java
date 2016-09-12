package org.song.cmd;

import org.junit.Test;
import org.song.cmd.support.DefaultCmdParser;

/**
 * Created by song on 16/9/4.
 */
public class CmdParserTest {


    @Test
    public void parse() throws Exception {
        CmdParser parser = new DefaultCmdParser();
        String[] args = new String[2];
        args[0] = "-classpath=/usr/jdk1.8/jre/lib/*";
        args[1] = "hello.java";
        parser.parse(args);
    }
}
