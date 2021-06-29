package com.zjw.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public @Data class User implements Serializable {
    private Integer userId;
    private String userName;
    private String userAddress;
    private String userSex;
    private Date userBirthday;
}
