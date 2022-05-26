package com.zjw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱俊伟
 */
@Getter @Setter
@ToString
public class User implements Serializable {

    /**
     * id字段
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 住址
     */
    private String userAddress;

    /**
     * 性别
     */
    private String userSex;

    /**
     * 出生日期
     */
    private Date userBirthday;
}
