package cn.itcast.eshop.common.action;

import cn.itcast.eshop.log.dao.ISysLog;
import cn.itcast.eshop.log.dao.impl.ConsoleLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Action：控制器类的基类
 * 1.封装请求数据
 * 2.校验权限
 * 3.调用服务层（Service）处理业务逻辑
 * 4.日志的记录
 * 5.返回消息到客户端
 */
public class BaseAction {
    //5.记录日志（待开发）
//    /** 上下文对象的key 登录用户 */
    public static final String LOGIN_USER = "LOGIN_USER";

    /**
     * 上下文对象 用来存储所有Aciton类的公共的信息
     */
    protected static final Map<String, Object> context = new HashMap<>();

    protected ISysLog log = new ConsoleLog();
}
