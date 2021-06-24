package com.zjw.dao;

import com.zjw.domain.QueryVo;
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
     * 根据queryVo中的条件查询用户
     * @param user
     * @return
     */
    List<User> findUserByVo(QueryVo queryVo);

    /**
     * 根据传入参数条件
     * @param user 查询的条件：有可能有用户名，有可能有性别，也有可能有地址，还有可能都有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     *根据queryVo中的id集合，查询用户信息
     * @param queryVo
     * @return
     */
    List<User> findUserByInIds(QueryVo queryVo);


}
