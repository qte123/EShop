package cn.itcast.eshop.common.dao;

import java.util.List;

/**
 * 访问数据库（文件）
 * 返回结果给DAO
 */
public interface IDataAccess {
    //List<User> getList(Class<User> clazz) throws Exception;
    <T> List<T> getList(Class<T> clazz) throws Exception;
}
