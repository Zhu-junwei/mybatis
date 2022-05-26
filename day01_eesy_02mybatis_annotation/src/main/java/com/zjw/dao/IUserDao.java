package com.zjw.dao;

import com.zjw.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 * @author 朱俊伟
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
