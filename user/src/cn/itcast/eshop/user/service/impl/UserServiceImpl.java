package cn.itcast.eshop.user.service.impl;

import cn.itcast.eshop.user.dao.UserDAO;
import cn.itcast.eshop.user.dao.impl.UserDAOImpl;
import cn.itcast.eshop.user.entity.User;
import cn.itcast.eshop.user.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    /**
     * 用户登录，根据用户名、密码获取用户对象
     * 1.调用UserDAO获取用户列表数据
     * List<User> getEntityList() throws Exception;
     * 2.遍历用户列表，逐个与给定用户对象的用户名、密码进行匹配
     * 3.匹配成功则返回该用户对象、失败返回null
     *
     * @param user 封装了用户名、密码的实体对象
     * @return 返回User对象，或者当用户名/密码错误时返回null
     * @throws Exception
     */
    @Override
    public User login(User user) throws Exception {
        //1.调用UserDAO获取用户列表数据
        userDAO = new UserDAOImpl();
        List<User> userList = userDAO.getEntityList();
        //2.遍历用户列表，逐个与给定用户对象的用户名、密码进行匹配
        if (userList != null) {
            for (User u : userList) {
                if (u.getUsername().equals(user.getUsername())
                        && u.getPassword().equals(user.getPassword())) {
                    return u;//匹配成功则返回该用户对象
                }

            }
        }
        return null;//失败返回null
    }
}
