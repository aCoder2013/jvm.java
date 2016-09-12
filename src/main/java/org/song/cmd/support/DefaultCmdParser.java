package org.song.cmd.support;

import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.song.classpath.ClassPath;
import org.song.cmd.CmdParser;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * 默认的命令行参数解析器
 * Created by song on 16/9/4.
 */
public class DefaultCmdParser implements CmdParser {

    private Options options;

    public DefaultCmdParser() {
        options = new Options();
        Option version = new Option(Version, false, "输出产品的版本并退出");
        Option help = new Option(HELP, "一些帮助信息");
        Option cp = Option.builder()
                .longOpt(CP)
                .hasArg()
                .desc("目录和zip/jar文件的类搜索路径")
                .build();
        Option classpath = Option.builder()
                .longOpt(CLASS_PATH)
                .hasArg()
                .desc("目录和zip/jar文件的类搜索路径;用:分隔的目录,JAR档案和ZIP档案列表, 用于搜索类文件")
                .build();
        options.addOption(version);
        options.addOption(help);
        options.addOption(classpath);
        options.addOption(cp);
    }

    public void parse(String[] args) throws Exception {
        CommandLineParser parser = new DefaultParser();
        CommandLine line = null;
        try {
            line = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("parse failed," + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        if (line.hasOption(Version)) {
            System.out.println("1.0.0");
        } else if (line.hasOption(HELP) || line.getArgList() == null || line.getArgList().size() <= 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java", options);
        } else {
            startJVM(line);
        }
    }

    private void startJVM(CommandLine line) throws Exception {
        String classpath = null;
        if (line.hasOption(CLASS_PATH) || line.hasOption(CP)) {
            classpath = line.getOptionValue(CLASS_PATH);
            if (StringUtils.isEmpty(classpath)) {
                classpath = line.getOptionValue(CP);
            }
        }
        ClassPath classPath = new ClassPath(classpath);
        List<String> argList = line.getArgList();
        if (argList == null || argList.size() > 1) {
            System.err.println("find more than one class");
            System.exit(-1);
        }
        String className = argList.get(0);
        byte[] bytes = null;
        try {
            bytes = classPath.readClass(className);
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }
        if (bytes == null || bytes.length <= 0) {
            System.err.println("class not exist ,or class content is null");
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        System.out.println(Integer.toHexString(byteBuffer.getInt()).toUpperCase());
        short minor = byteBuffer.getShort();
        short major = byteBuffer.getShort();
        System.out.println(major + "." + minor);
    }
}
