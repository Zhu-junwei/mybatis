package com.zjw.test;

import com.zjw.dao.IRoleDao;
import com.zjw.dao.IUserDao;
import com.zjw.domain.Role;
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

public class RoleTest {

    private InputStream in ;
    private SqlSessionFactoryBuilder builder;
    private SqlSessionFactory factory ;
    private SqlSession session ;
    private IRoleDao roleDao ;

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
        roleDao = session.getMapper(IRoleDao.class);
    }

    @After
    public void destory() throws Exception{
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
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }


}
