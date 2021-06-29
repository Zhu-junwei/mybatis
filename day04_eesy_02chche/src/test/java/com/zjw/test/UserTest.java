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

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class UserTest {

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
        //使用工厂生成SqlSession对象
        session = factory.openSession();
        //使用SqlSession穿过将Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception{
        session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查询操作
     */
    @Test
    public void testFindAll(){

        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);

        }
    }

    /**
     * 测试一级缓存
     * 可以发现拿到的是同一个对象
     *
     * 一级缓存是SqlSession范围的缓存，当调用SqlSession的修改，添加，删除，commit(),close()方法时，一级缓存会给清空
     */
    @Test
    public void testFindUserById(){
        User user1 = userDao.findById(41);
        User user2 = userDao.findById(41);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    /**
     * 测试一级缓存
     * 关闭session可以清空一级缓存
     */
    @Test
    public void testFindUserById2(){
        User user1 = userDao.findById(41);
        //关闭session
        session.close(); //close可以清空缓存
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);

//        session.clearCache();//clearCache也可以清空缓存

        User user2 = userDao.findById(41);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    /**
     * 测试一级缓存
     * clearCache清空一级缓存
     */
    @Test
    public void testFindUserById3(){
        User user1 = userDao.findById(41);
        session.clearCache();//clearCache也可以清空缓存

        User user2 = userDao.findById(41);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    @Test
    public void testObjects(){

        boolean equals = Objects.equals("A", "B");

    }

}
