package cn.itcast.eshop.user.dao;

import cn.itcast.eshop.common.dao.BaseDAO;
import cn.itcast.eshop.user.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO {
    List<User> getEntityList() throws Exception;
}
