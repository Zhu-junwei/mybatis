package com.zjw.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱俊伟
 */
@Data
public class User implements Serializable {

    /**
     * id字段
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 住址
     */
    private String address;
}
