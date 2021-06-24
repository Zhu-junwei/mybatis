package com.zjw.dao;

import com.zjw.domain.Account;
import com.zjw.domain.AccountUser;
import com.zjw.domain.User;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {

    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    /**
     * 根据用户id查询用户的所有账户
     * @param uid
     * @return
     */
    List<Account> findAccountByUid(Integer uid);

    /**
     *-查询所有账户同时包含用户地址和名字
     * @return
     */
    List<AccountUser> findAllAccount();

}
