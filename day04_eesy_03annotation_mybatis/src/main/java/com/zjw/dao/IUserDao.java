package com.zjw.dao;

import com.zjw.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 在mybatis中针对CRUD一共有四个注解
 * @author 朱俊伟
 * @Select @Insert @Update @Delete
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */

//    只有一个value需要赋值时，value可以省略
//    @Select(value = "")
    @Select("select * from user")
    @Results(
            id = "userMap",
            value = {
                    @Result(id = true,column = "id",property = "userId"),
                    @Result(column = "username",property = "userName"),
                    @Result(column = "birthday",property = "userBirthday"),
                    @Result(column = "sex",property = "userSex"),
                    @Result(column = "address",property = "userAddress"),
            }
    )
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("INSERT INTO user(username,address,sex,birthday) VALUES (#{userName},#{userAddress},#{userSex},#{userBirthday})")
    @SelectKey(keyColumn = "id",keyProperty = "userId",resultType = Integer.class,before = false,statement = {"select last_insert_id()"})
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("UPDATE user set username=#{userName},address=#{userAddress},sex=#{userSex},birthday=#{userBirthday} WHERE id=#{userId}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("Delete FROM user WHERE id=#{userId}")
    void deleteUser(Integer id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id=#{userId}")
    @ResultMap("userMap")
    User findById(Integer id);

    /**
     * 根据名字模糊查询用户
     * @param name
     * @return
     */
    //方法一:传入的name需要加%号
//    @Select("SELECT * FROM user WHERE username LIKE #{name}")
    //方法二:并不需要加%号，防止sql注入
    @Select("SELECT * FROM user WHERE username LIKE '%${value}%'")
    @ResultMap("userMap")
    List<User> findByName(String name);


    /**
     * 查询用户总记录数
     * @return
     */
    @Select("SELECT COUNT(*) FROM user")
    int findTotal();
}
