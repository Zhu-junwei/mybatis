package com.zjw.test;

import com.zjw.dao.IUserDao;
import com.zjw.domain.QueryVo;
import com.zjw.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestDao {

    private InputStream in ;
    private SqlSession session ;
    private IUserDao userDao ;

    @Before
    public void init() throws Exception{
        //读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //使用工厂生成SqlSession对象
//        session = factory.openSession();
        session = factory.openSession(true);
        //使用SqlSession穿过将Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception{
//        session.commit();
        session.close();
        in.close();
    }

    /**
     * 测试查询操作
     */
    @Test
    public void testFindAll(){
        userDao = session.getMapper(IUserDao.class);
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 测试保存操作
     */
    @Test
    public void testSaveUser(){

        User user = new User();
        user.setUserName("朱俊伟1231231232");
        user.setUserAddress("河南");
        user.setUserBirthday(new Date());
        user.setUserSex("男");

        userDao.saveUser(user);
    }

    /**
     * 测试保存返回id操作
     */
    @Test
    public void testSaveUserReturnId(){

        User user = new User();
        user.setUserName("testInsert id");
        user.setUserAddress("河南");
        user.setUserBirthday(new Date());
        user.setUserSex("男");

        System.out.println(user);//插入前打印无id
        userDao.saveUserReturnId(user);
        System.out.println(user);//插入后打印有id
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdateUser(){

        User user = new User();
        user.setUserId(50);
        user.setUserName("朱俊伟");
        user.setUserAddress("河南11111111");
        user.setUserBirthday(new Date());
        user.setUserSex("男");

        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(48);
    }

    /**
     * 测试查询用户
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(50);
        System.out.println(user);
    }

    /**
     * 测试根据名字模糊查询用户
     */
    @Test
    public void testFindByName(){
        //使用#{} , 需要加%
//        List<User> userList = userDao.findByName("%王%");
        //使用${} , 不用加%
        List<User> userList = userDao.findByName("王");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试QueryVo查询用户
     */
    @Test
    public void testFindUserByVo(){
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        queryVo.setUser(user);

        List<User> userList = userDao.findUserByVo(queryVo);
        for (User u : userList) {
            System.out.println(u);
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
