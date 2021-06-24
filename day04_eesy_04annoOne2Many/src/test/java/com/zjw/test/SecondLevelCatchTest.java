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
import java.util.List;

public class SecondLevelCatchTest {

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

        in.close();
    }


    /**
     * 测试查找单个用户，验证二级缓存
     */
    @Test
    public void testFindById(){
        //第一次查询数据库
        User user = userDao.findById(54);
        System.out.println(user);

        session.close();
        session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        //第二次用cache中取值
        User user2 = userDao.findById(54);
        System.out.println(user2);

        System.out.println(user == user2);

        session.commit();
        session.close();

    }


}
