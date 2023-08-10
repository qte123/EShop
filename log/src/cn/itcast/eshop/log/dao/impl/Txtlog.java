package cn.itcast.eshop.log.dao.impl;

import cn.itcast.eshop.log.dao.ISysLog;

/**
 * 日志实现类
 * 在控制台打印日志信息
 *
 * 步骤
 * 1.封装日志对象
 * 2.打印日志数据到Txtlog.txt中
 */
public class Txtlog implements ISysLog {
    @Override
    public void info(String msg) {

    }

    @Override
    public void warm(String msg) {

    }

    @Override
    public void error(String msg) {

    }
}
