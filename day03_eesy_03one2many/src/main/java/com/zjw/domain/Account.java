package com.zjw.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 朱俊伟
 */
@Data
public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private Double money;

    /**
     * 一个账户输入一个用户
     */
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
