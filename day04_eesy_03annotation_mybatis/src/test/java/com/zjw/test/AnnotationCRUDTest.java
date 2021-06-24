package com.zjw.test;

import com.zjw.dao.IUserDao;
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

public class AnnotationCRUDTest {

    private InputStream in ;
    private SqlSessionFactory factory ;
    private SqlSession session ;
    private IUserDao userDao ;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 测试查询所有用户
     */
    @Test
    public void testFindAll(){
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存用户
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("mybatis annotation");
        user.setUserAddress("上海");
        userDao.saveUser(user);
        System.out.println(user);
    }

    /**
     * 测试更新用户
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(57);
        user.setUserName("mybatis");
        user.setUserAddress("上海");
        user.setUserBirthday(new Date());
        user.setUserSex("男");

        userDao.updateUser(user);
    }


    /**
     * 测试删除用户
     */
    @Test
    public void testDelete(){
        userDao.deleteUser(57);
    }

    /**
     * 测试查找单个用户
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(41);
        System.out.println(user);

    }

    /**
     * 根据名字模糊查询用户
     */
    @Test
    public void testFindByName(){
//        List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        for (User user : users){
            System.out.println(user);
        }
    }

    /**
     * 查询总记录数
     */
    @Test
    public void testFindTotal(){
        System.out.println(userDao.findTotal());
    }

}
