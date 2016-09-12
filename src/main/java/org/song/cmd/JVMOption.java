package org.song.cmd;

/**
 * 表示可传入的一些命令行参数
 * <p>
 * Created by song on 16/9/4.
 */
public class JVMOption {

    /**
     * 版本信息
     */
    private String version;

    /**
     * 类路径
     */
    private String classpath;

    /**
     * 类路径的别名
     */
    private String cp;

    /**
     * 表示类名称
     */
    private String className;

    public String getVersion() {
        return version;
    }

    public JVMOption setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getClasspath() {
        return classpath;
    }

    public JVMOption setClasspath(String classpath) {
        this.classpath = classpath;
        return this;
    }

    public String getCp() {
        return cp;
    }

    public JVMOption setCp(String cp) {
        this.cp = cp;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public JVMOption setClassName(String className) {
        this.className = className;
        return this;
    }
}
