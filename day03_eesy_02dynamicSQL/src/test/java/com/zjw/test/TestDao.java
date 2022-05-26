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
import java.util.ArrayList;
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
        userDao = session.getMapper(IUserDao.class);
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
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
     * 根据传入参数查询用户
     */
    @Test
    public void testFindByCondition(){
        User user = new User();
        user.setUserName("老王");
        user.setUserSex("男");
        List<User> userList = userDao.findUserByCondition(user);
        for (User u : userList) {
            System.out.println(u);
        }
    }

    /**
     * 根据queryVo中的id集合，查询用户信息
     */
    @Test
    public void testFindUserByInIds(){
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(42);
        ids.add(46);
        queryVo.setIds(ids);
        List<User> userList = userDao.findUserByInIds(queryVo);
        for (User u : userList) {
            System.out.println(u);
        }
    }


}
