package com.laj.dao;

import com.laj.domain.QueryVo;
import com.laj.domain.User;

import javax.management.Query;
import java.util.List;

public interface UserDao {
    List<User> findAll();//查询所有用户
    User findById(Integer userid);//根据id模糊查询
    List<User> findByName(String username);//根据name模糊查询
    List<User> findUserByVo(QueryVo vo);
    List<User> findUserByCondition(User user);//根据传入参数的条件
    List<User> findUserInIds(QueryVo vo);//根据QueryVo中提供的ID集合进行用户列表查询
}
