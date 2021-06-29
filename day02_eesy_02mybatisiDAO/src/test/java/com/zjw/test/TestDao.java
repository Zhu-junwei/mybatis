package com.zjw.test;

import com.zjw.dao.IUserDao;
import com.zjw.dao.impl.UserDaoImpl;
import com.zjw.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestDao {

    private InputStream in ;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory ;
    private SqlSession session ;
    private IUserDao userDao ;

    @Before
    public void init() throws Exception{
        //读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        //使用工厂生成dao对象
        userDao = new UserDaoImpl(factory);
    }

    @After
    public void destroy() throws Exception{
        //释放资源
        in.close();
    }

    /**
     * 测试查询操作
     * @throws IOException
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 测试保存操作
     * @throws IOException
     */
    @Test
    public void testSaveUser(){

        User user = new User();
        user.setUsername("testInsert user...");
        user.setAddress("河南");
        user.setBirthday(new Date());
        user.setSex("男");

        userDao.saveUser(user);
    }

    /**
     * 测试保存返回id操作
     * @throws IOException
     */
    @Test
    public void testSaveUserReturnId(){

        User user = new User();
        user.setUsername("testInsert user...");
        user.setAddress("河南");
        user.setBirthday(new Date());
        user.setSex("男");

        userDao.saveUser(user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdateUser(){

        User user = new User();
        user.setId(50);
        user.setUsername("朱俊伟111");
        user.setAddress("河南11111111");
        user.setBirthday(new Date());
        user.setSex("男");

        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(53);
    }

    /**
     * 测试查询用户
     */
    @Test
    public void testfindById(){
        User user = userDao.findById(50);
        System.out.println(user);
    }

    /**
     * 测试根据名字模糊查询用户
     */
    @Test
    public void testFindByName(){
        //使用#{} , 需要加%
        List<User> userList = userDao.findByName("%王%");
        //使用${} , 不用加%
//        List<User> userList = userDao.findByName("王");
        for (User user : userList) {
            System.out.println(user);
        }
    }


    /**
     * 查询用户的条数
     */
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }



}
