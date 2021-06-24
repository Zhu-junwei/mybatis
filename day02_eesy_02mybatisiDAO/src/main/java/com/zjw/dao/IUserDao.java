package com.zjw.dao;

import com.zjw.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    void saveUserReturnId(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名字模糊查询用户
     * @param userName
     * @return
     */
    List<User> findByName(String userName);

    /**
     * 查询用户的所有记录数
     * @return
     */
    int findTotal();
}
