package com.zjw.dao;

import com.zjw.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户信息
     * @return
     */
    @Select("SELECT * FROM account")
    @Results(id = "AccountMap", value = {
            @Result(id = true ,property = "id" ,column = "id"),
            @Result(property = "uid" ,column = "uid"),
            @Result(property = "money" ,column = "money"),
            @Result(property ="user", column = "uid", one = @One(select = "com.zjw.dao.IUserDao.findById" ,fetchType = FetchType.EAGER))

    })
    List<Account> findAll();

    /**
     * 通过用户id查找用户的账户
     * @param uid
     * @return
     */
    @Select("SELECT * FROM account WHERE uid = #{uid}")
    List<Account> findAccountByUid(Integer uid);
}
