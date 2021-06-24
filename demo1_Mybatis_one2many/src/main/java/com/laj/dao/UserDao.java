package com.laj.dao;

import com.laj.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();//同时获取到用户下所有的账户信息
    User findById(Integer userid);//根据id模糊查询
}
