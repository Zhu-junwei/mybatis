package com.zjw.test;

import com.zjw.dao.IAccountDao;
import com.zjw.domain.Account;
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

public class AccountTest {

    private InputStream in ;
    private SqlSession session ;
    private IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }


    @Test
    public void testFindAll(){
        //立即加载，会同时查询用户信息
        List<Account> accounts = accountDao.findAll();
//        for (Account account : accounts) {
//            System.out.println("----每个账户的信息----");
//            System.out.println(account);
//            System.out.println(account.getUser());
//        }
    }

}
