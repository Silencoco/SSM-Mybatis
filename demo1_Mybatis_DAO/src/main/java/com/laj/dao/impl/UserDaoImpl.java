package com.laj.dao.impl;

import com.laj.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements com.laj.dao.UserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }
    @Override
    public List<User> findAll() {
//        根据factory获取session对象
        SqlSession session = factory.openSession();
//        调用session中的方法实现查询列表
        List<User> users = session.selectList("com.laj.dao.UserDao.findAll");//参数：能获取到配置信息的key
//        释放资源
        session.close();
        return users;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Integer userid) {

    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public User findById(Integer userid) {
        return null;
    }

    @Override
    public List<User> findByName(String username) {
        return null;
    }

    @Override
    public int findTotal() {
        return 0;
    }

    @Override
    public void saveUser(User user) {

    }
}
