package cn.itcast.eshop.log.dao;

public interface ISysLog {
    /** 日志级别 普通消息 */
    public static final String INFO="INFO";
    /** 日志级别 警告消息 */
    public static final String WARM="WARM";
    /** 日志级别 错误消息 */
    public static  final String ERROR="ERROR";

    void info(String msg);
    void warm(String msg);
    void error(String msg);
}
