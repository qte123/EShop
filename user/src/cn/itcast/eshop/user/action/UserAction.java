package cn.itcast.eshop.user.action;

import cn.itcast.eshop.common.action.BaseAction;
import cn.itcast.eshop.common.entity.Msg;
import cn.itcast.eshop.common.util.JSONUtil;
import cn.itcast.eshop.user.entity.User;
import cn.itcast.eshop.user.service.UserService;
import cn.itcast.eshop.user.service.impl.UserServiceImpl;

/**
 * 用户控制器类
 * 处理所有用户的后台操作，并返回JSON格式的字符串消息
 */
public class UserAction extends BaseAction {

    // 用户名
    private String username;
    // 密码
    private String password;

    /**
     * 用户登录功能
     * 1.封装数据到User对象
     * 2.调用UserService处理逻辑
     * User login(User user) throws Exception;
     * 3.异常处理
     * 4.根据服务层返回结果生成消息
     * 消息实体类Msg
     * 5.记录日志（待开发）
     * 6.响应消息到客户端
     *
     * @return 返回处理消息，JSON格式
     */
    public String login() {
        Msg msg = new Msg();
        try {
            // 1.封装数据到User对象
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            // 2.调用UserService处理逻辑
            //     User login(User user) throws Exception;
            UserService userService = new UserServiceImpl();
            user = userService.login(user);

            // 3.异常处理

            // 4.根据服务层返回结果生成消息
            //      	消息实体类Msg
            if (user != null) {
                // 把当前登录用户对象放到上下文对象中
                context.put(LOGIN_USER, user);
                msg.setType(Msg.SUCCESS); // 登录成功
                msg.setMsg("登录成功");
                // 5.记录日志
                log.info(username + "同学已登录");
            } else {
                msg.setType(Msg.FAIL); // 登录成功
                msg.setMsg("用户名或密码不正确");
            }
            // 6.响应消息到客户端
            return JSONUtil.entityToJSON(msg);
        } catch (Exception e) {
            log.error(e.getMessage());
            msg.setType(Msg.FAIL); // 登录失败
            msg.setMsg("服务器异常");
            return JSONUtil.entityToJSON(msg);
        }
    }

    /**
     * 获取当前登录用户对象
     *
     * @return 返回用户对象，或null
     */
    public User getLoginUser() {
        Object obj = context.get(LOGIN_USER);
        if (obj != null) {
            return (User) obj;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
