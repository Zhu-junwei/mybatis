package com.zjw.test;

import com.zjw.dao.IUserDao;
import com.zjw.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisAnnoTest {
    public static void main(String[] args) throws IOException {
        //1、获取字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3、根据sqlSessionFactory生产一个SqlSession
        SqlSession sqlSession = factory.openSession();
        //4、根据SqlSession获取Dao的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        //6、释放资源
        sqlSession.close();
        in.close();
    }
}
