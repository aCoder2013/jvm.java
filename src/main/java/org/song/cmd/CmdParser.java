package org.song.cmd;

/**
 * Created by song on 16/9/4.
 */
public interface CmdParser {

    String HELP = "help";

    String Version = "version";

    String CLASS_PATH = "classpath";

    String CP = "cp";

    String CLASS = "class";

    String ARGS = "args";


    /**
     * 负责解析命令行参数
     *
     * @return
     */
    void parse(String[] args) throws Exception;

}
