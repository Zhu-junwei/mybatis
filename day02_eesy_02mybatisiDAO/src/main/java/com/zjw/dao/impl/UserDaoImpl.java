package com.zjw.dao.impl;

import com.zjw.dao.IUserDao;
import com.zjw.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author 朱俊伟
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory ;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory ;
    }

    @Override
    public List<User> findAll() {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现查询列表
        //参数就是能获取配置信息的key(namespace+id)
        List<User> users = session.selectList("com.zjw.dao.IUserDao.findAll");
        //3、释放资源
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        //参数就是能获取配置信息的key(namespace+id),要操作的对象
        session.insert("com.zjw.dao.IUserDao.saveUser",user);
        //3、释放资源
        session.commit();
        session.close();
    }

    @Override
    public void saveUserReturnId(User user) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        //参数就是能获取配置信息的key(namespace+id),要操作的对象
        session.insert("com.zjw.dao.IUserDao.saveUserReturnId",user);
        //3、释放资源
        session.close();
    }

    @Override
    public void updateUser(User user) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        //参数就是能获取配置信息的key(namespace+id)
        session.update("com.zjw.dao.IUserDao.updateUser",user);
        //3、释放资源
        session.commit();
        session.close();
    }

    @Override
    public void deleteUser(Integer userId) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        //参数就是能获取配置信息的key(namespace+id)
        session.delete("com.zjw.dao.IUserDao.deleteUser",userId);
        //3、释放资源
        session.commit();
        session.close();
    }

    @Override
    public User findById(Integer userId) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        //参数就是能获取配置信息的key(namespace+id)
        User user = session.selectOne("com.zjw.dao.IUserDao.findById", userId);
        //3、释放资源
        session.close();
        return user ;
    }

    @Override
    public List<User> findByName(String userName) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        //参数就是能获取配置信息的key(namespace+id)
        List<User> users = session.selectList("com.zjw.dao.IUserDao.findByName", userName);
        //3、释放资源
        session.close();
        return users ;
    }

    @Override
    public int findTotal() {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        //参数就是能获取配置信息的key(namespace+id)
        int count = session.selectOne("com.zjw.dao.IUserDao.findTotal");
        //3、释放资源
        session.close();
        return count ;
    }
}
