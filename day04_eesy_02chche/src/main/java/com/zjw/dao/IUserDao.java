package com.zjw.dao;

import com.zjw.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 * @author 朱俊伟
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

}
