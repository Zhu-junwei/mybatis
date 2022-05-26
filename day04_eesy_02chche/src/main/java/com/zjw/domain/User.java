package com.zjw.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱俊伟
 */
@Getter @Setter
public class User implements Serializable{

    private Integer id;
    private String username;
    private String address;
    private String sex;
    private Date birthday;

}
