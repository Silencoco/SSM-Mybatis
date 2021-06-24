package com.laj.test;

import com.laj.dao.UserDao;
import com.laj.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    public static void main(String[] args) throws IOException {
//        1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
//        3.使用工厂生产sqlSession对象
        SqlSession session = factory.openSession();
//        4.使用SqlSession创建Dao接口的代理对象
        UserDao userDao = session.getMapper(UserDao.class);
//        5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        /*
        创建工厂引入配置文件--打开会话--获取映射字节码文件--获取方法
         */
//        List<User> users = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("SqlMapConfig.xml")).openSession().getMapper(UserDao.class).findAll();
        for (User user : users) {
            System.out.println(user);
        }
//        6.释放资源
        session.close();
        in.close();
    }
}
