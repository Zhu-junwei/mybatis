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

public class SecondLeveCacheTest {

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
//        //使用工厂生成SqlSession对象
//        session = factory.openSession();
//        //使用SqlSession穿过将Dao接口的代理对象
//        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception{
//        session.commit();
//        session.close();
        in.close();
    }

    /**
     * 测试二级缓存
     * SqlSessionFactory级别的缓存
     * 二级缓存的使用步骤：
     *  第一步：让Mybatis框架支持二级缓存（在SqlMapConfig.xml中配置）
     *  第二步：让当前的映射文件支持二级缓存（在IUserDao.xml中配置）
     *  第三步：让当前的操作支持二级缓存（在select标签中配置）
     */
    @Test
    public void testFindUserById(){

        SqlSession session1 = factory.openSession();
        IUserDao userDao1 = session1.getMapper(IUserDao.class);
        User user1 = userDao1.findById(41);
        System.out.println(user1);
        //关闭一级缓存
        session1.close();

        SqlSession session2 = factory.openSession();
        IUserDao userDao2 = session2.getMapper(IUserDao.class);
        /*
         * 发现只查询了一次数据库 但是两者比较为false
         * 因为缓存中存放的是数据，不是对象
         */
        User user2 = userDao2.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);//false,不是一个对象
        System.out.println(user1.equals(user2));//true，但是对象的属性值是一样的
    }

}
