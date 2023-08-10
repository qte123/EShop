package cn.itcast.eshop.user.entity;

import cn.itcast.eshop.common.entity.Entity;

public class Person extends Entity {
    //姓名
    private String name;
    //性别
    private String sex;
    //年龄
    private int age;
    //所在地址
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
