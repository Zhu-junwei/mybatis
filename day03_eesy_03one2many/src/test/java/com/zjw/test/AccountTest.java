package com.zjw.test;

import com.zjw.dao.IAccountDao;
import com.zjw.dao.IUserDao;
import com.zjw.domain.Account;
import com.zjw.domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {

    private InputStream in ;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory ;
    private SqlSession session ;
    private IUserDao userDao ;
    private IAccountDao accountDao;

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
        accountDao = session.getMapper(IAccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 查询所有账户同时包含用户地址和名字
     */
    @Test
    public void testFindAllAccount(){

        //使用代理对象执行方法
        List<AccountUser> accountUsers = accountDao.findAllAccount();
        for (AccountUser accountUser : accountUsers) {
            System.out.println(accountUser);
        }
    }

}
