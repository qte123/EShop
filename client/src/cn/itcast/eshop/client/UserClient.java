package cn.itcast.eshop.client;

import cn.itcast.eshop.common.entity.Msg;
import cn.itcast.eshop.common.util.JSONUtil;
import cn.itcast.eshop.user.action.UserAction;

/**
 * 用户操作界面
 * 所有和用户操作相关的内容，都放到这个类里
 */
public class UserClient extends Client {
    /**
     * 用户登录操作页面
     * 1.使用控制台提示用户输入用户名、密码
     * 2.向服务器发送请求，并接收返回消息字符串
     * 使用setter方法把数据传递给Action
     * 调用Action的登录功能
     * 3.解析消息字符串，提示用户信息
     * 4.页面跳转：使用字符串常量作为标记
     * 成功：返回上一次操作的页面
     * 失败：返回登录页面
     *
     * @return
     */
    public String showLogin() {
        //1.使用控制台提示用户输入用户名、密码
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        //2.向服务器发送请求，并接收返回消息字符串
        UserAction userAction = new UserAction();
        //2.1使用setter方法把数据传递给Action
        userAction.setUsername(username);
        userAction.setPassword(password);
        //2.2调用Action的登录功能
        String result = userAction.login();

        //3.解析消息字符串，提示用户信息
        Msg msg = JSONUtil.JSONToEntity(result, Msg.class);
        //4.页面跳转：使用字符串常量作为标记
        //成功：返回上一次操作的页面
        //失败：返回登录页面
        if (msg.getType().equals(Msg.SUCCESS)) {//登录成功
            System.out.println(msg.getMsg());
            return HISTORY;
        } else {//登录失败
            System.out.println(msg.getMsg());
            return LOGIN;
        }
    }
}
