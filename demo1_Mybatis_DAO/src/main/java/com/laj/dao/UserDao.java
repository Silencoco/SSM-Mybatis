package com.laj.dao;

import com.laj.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();//查询所有用户
//    void saveUser(User user);
    void updateUser(User user);//更新
    void deleteUser(Integer userid);//删除
    void insertUser(User user);//增加（插入）
    User findById(Integer userid);//根据id模糊查询
    List<User> findByName(String username);//根据name模糊查询
    int findTotal();//获取用户总记录条数
    void saveUser(User user);
}
