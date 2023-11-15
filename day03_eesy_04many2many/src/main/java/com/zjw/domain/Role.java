package com.zjw.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * @author 朱俊伟
 */
@Data
public class Role implements Serializable {
    private Integer roleId;
    private String roleName;
    private String roleDesc;

    /**
     *一个角色对应多个用户
     */
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
