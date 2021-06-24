package com.zjw.dao;

import com.zjw.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 在mybatis中针对CRUD一共有四个注解
 * @Select @Insert @Update @Delete
 */

@CacheNamespace(blocking = true)//mybatis 基于注解方式实现配置二级缓存
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */

//    只有一个value需要赋值时，value可以省略
//    @Select(value = "")
    @Select("select * from user")
    @Results(id = "UserMap" , value = {
                    @Result(id=true,column = "id",property = "userId"),
                    @Result(column = "username",property = "userName"),
                    @Result(column = "sex",property = "userSex"),
                    @Result(column = "address",property = "userAddress"),
                    @Result(column = "birthday",property = "userBirthday"),
                    @Result(property = "accounts",column = "id",
                            many = @Many(select ="com.zjw.dao.IAccountDao.findAccountByUid",
                                        fetchType = FetchType.LAZY))
            }
    )
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
//    @ResultMap(value = "UserMap")
//    @ResultMap("UserMap")
    @ResultMap(value = {"UserMap"})
    User findById(Integer id);

    /**
     * 根据名字模糊查询用户
     * @param name
     * @return
     */
    //方法一:传入的name需要加%号
    @Select("SELECT * FROM user WHERE username LIKE #{name}")
    //方法二:并不需要加%号，防止sql注入
//    @Select("SELECT * FROM user WHERE username LIKE '%${value}%'")
    @ResultMap(value = {"UserMap"})
    List<User> findByName(String name);

}
