package cn.itcast.eshop.log.entity;

/**
 * 日志实体类
 */
public class Log {
    //日志内容
    private String msg;
    //日志级别
    private String level;
    //日志发生时间
    private String time;

    public Log() {
    }

    /**
     * 构造方法封装日志数据
     *
     * @param msg   日志内容
     * @param level 日志级别
     * @param time  日志发生时间
     */
    public Log(String msg, String level, String time) {
        this.msg = msg;
        this.level = level;
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        //[时间] 级别：消息内容
        return "["+time+"] "+level+": "+msg;
    }
}
