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

public class UserTest {

    private InputStream in ;
    private SqlSession session ;
    private IUserDao userDao ;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }


    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        //验证延迟加载
//        for (User user : users) {
//            System.out.println(user);
//            System.out.println(user.getAccounts());
//        }
    }
}
