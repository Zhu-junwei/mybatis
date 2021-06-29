package com.zjw.domain;

import lombok.Data;

@Data
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
