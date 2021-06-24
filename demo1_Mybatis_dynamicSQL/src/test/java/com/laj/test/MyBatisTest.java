package com.laj.test;

import com.laj.dao.UserDao;
import com.laj.domain.QueryVo;
import com.laj.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.management.Query;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisTest {
    private InputStream in= null;
    private SqlSession sqlSession=null;
    private UserDao userDao = null;

    /**
     * 初始化
     * @throws IOException
     */
    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();//在参数中添加true可以实现自动提交
        userDao = sqlSession.getMapper(UserDao.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After
    public void destroy() throws Exception {
        //提交事务  其实效率低，并不是每次操作都需要保存（如查询操作）
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询操作
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 根据id查询用户
     */
    @Test
    public void findById(){
        User user=userDao.findById(12);
        System.out.println(user);
    }
    /**
     * 根据name模糊查询
     */
    @Test
    public void findByName(){
        List<User> user=userDao.findByName("%文理学生%");
        for (User user1 : user) {
            System.out.println(user1);
        }
    }
    /**
     * 根据query vo中的条件查询用户
     * @param
     * @return
     */
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUser_name("%文理学生%");
        vo.setUser(user);
        List<User> users=userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

    /**
     * 实现多种条件查询-->SQL动态查询
     */
    @Test
    public void testFindByCondition(){
        User user1 = new User();
        user1.setUser_pwd("root");
        user1.setUser_type(1);
        List<User> users = userDao.findUserByCondition(user1);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据QueryVo中提供的ID集合进行用户列表查询
     * foreach查询
     */
    @Test
    public void testFindUserInIds(){
        QueryVo vo = new QueryVo();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(21);
        list.add(25);
        list.add(29);
        vo.setIds(list);
        List<User> users = userDao.findUserInIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }
//    /**
//     * 测试保存操作
//     */
//    @Test
//    public void saveUser(){
//        User user = new User();
//        user.setUser_id(20);
//        user.setUser_name("彭于晏");
//        user.setUser_pwd("root");
//        user.setUser_type(1);
//        userDao.saveUser(user);
//    }
}
