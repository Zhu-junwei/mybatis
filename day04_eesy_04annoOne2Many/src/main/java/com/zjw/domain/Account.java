package com.zjw.domain;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private Double money;

    //多对一（mybatis中称为一对一）的映射：一个账户只能属于一个用户
    private User user;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
