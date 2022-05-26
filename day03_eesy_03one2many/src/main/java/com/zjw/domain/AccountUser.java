package com.zjw.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 朱俊伟
 */
@Getter @Setter
public class AccountUser extends Account {
    private String address ;
    private String username;

    @Override
    public String toString() {
        return super.toString()+"  AccountUser{" +
                "address='" + address + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
