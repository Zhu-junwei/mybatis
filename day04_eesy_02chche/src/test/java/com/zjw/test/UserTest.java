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

public class UserTest {

    private InputStream in ;
    private SqlSessionFactory factory ;
    private SqlSession session ;
    private IUserDao userDao ;

    @Before
    public void init() throws Exception{
        //读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
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
        // id一样，只查询一次数据库，因为一个session
        User user1 = userDao.findById(41);
        User user2 = userDao.findById(41);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1 == user2);//true
    }

    /**
     * 测试一级缓存
     * 一级缓存失效四种情况：1、不是同一个session
     *  关闭session可以清空一级缓存
     */
    @Test
    public void testFindUserById2(){
        User user1 = userDao.findById(41);
        //关闭session
        session.close(); //close可以清空缓存
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
        //发现再次执行的sql查询操作 一级缓存失效
        User user2 = userDao.findById(41);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1 == user2);//false
    }

    /**
     * 测试一级缓存
     * 一级缓存失效四种情况：2、手动清空了一级缓存。
     * clearCache清空一级缓存
     */
    @Test
    public void testFindUserById3(){
        User user1 = userDao.findById(41);
        //clearCache也可以清空一级缓存
        session.clearCache();

        User user2 = userDao.findById(41);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1 == user2);//false
    }

    /**
     * 测试一级缓存
     * 一级缓存失效四种情况：3、查询条件不同
     */
    @Test
    public void testFindUserById4(){
        User user1 = userDao.findById(41);
        User user2 = userDao.findById(42);
        User user3 = userDao.findById(41);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user1 == user2);//false
        System.out.println(user1 == user3);//true
    }

    /**
     * 测试一级缓存
     * 一级缓存失效四种情况：4、在两次相同查询条件中间执行过增删改操作。(因为中间的增删改可能对缓存中数据进行修改，所以不能用)
     */
    @Test
    public void testFindUserById5(){
        User user1 = userDao.findById(41);
        System.out.println(user1);
        //执行增删改操作
        user1.setUsername("user11");
        userDao.updateUser(user1);
        //发现查询sql执行了两次
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);//false
    }


}
