package com.zjw.dao.impl;

import com.zjw.dao.IUserDao;
import com.zjw.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory ;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory ;
    }

    public List<User> findAll() {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.zjw.dao.IUserDao.findAll");//参数就是能获取配置信息的key(namespace+id)
        //3、释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        session.insert("com.zjw.dao.IUserDao.saveUser",user);//参数就是能获取配置信息的key(namespace+id),要操作的对象
        //3、释放资源
        session.commit();
        session.close();
    }

    public void saveUserReturnId(User user) {

    }

    public void updateUser(User user) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        session.update("com.zjw.dao.IUserDao.updateUser",user);//参数就是能获取配置信息的key(namespace+id)
        //3、释放资源
        session.commit();
        session.close();
    }

    public void deleteUser(Integer userId) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        session.delete("com.zjw.dao.IUserDao.deleteUser",userId);//参数就是能获取配置信息的key(namespace+id)
        //3、释放资源
        session.commit();
        session.close();
    }

    public User findById(Integer userId) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        User user = session.selectOne("com.zjw.dao.IUserDao.findById", userId);//参数就是能获取配置信息的key(namespace+id)
        //3、释放资源
        session.close();
        return user ;
    }

    public List<User> findByName(String userName) {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        List<User> users = session.selectList("com.zjw.dao.IUserDao.findByName", userName);//参数就是能获取配置信息的key(namespace+id)
        //3、释放资源
        session.close();
        return users ;
    }

    public int findTotal() {
        //1、获取SqlSession对象
        SqlSession session = factory.openSession();
        //2、调用SqlSession中的方法，实现保存用户操作
        int count = session.selectOne("com.zjw.dao.IUserDao.findTotal");//参数就是能获取配置信息的key(namespace+id)
        //3、释放资源
        session.close();
        return count ;
    }
}
