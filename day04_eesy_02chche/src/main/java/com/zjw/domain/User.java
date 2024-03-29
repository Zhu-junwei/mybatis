package com.zjw.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 朱俊伟
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String username;
    private String address;
    private String sex;
    private Date birthday;

}
