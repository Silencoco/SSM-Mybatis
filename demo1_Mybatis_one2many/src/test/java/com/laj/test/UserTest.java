package com.laj.test;

import com.laj.dao.AccountDao;
import com.laj.dao.UserDao;
import com.laj.domain.Account;
import com.laj.domain.AccountUser;
import com.laj.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    private InputStream in= null;
    private SqlSession sqlSession=null;
    private UserDao userdao = null;

    /**
     * 初始化
     * @throws IOException
     */
    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();//在参数中添加true可以实现自动提交
        userdao = sqlSession.getMapper(UserDao.class);
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
     * 查询所有账户
     */
    @Test
    public void testFindAll(){
        List<User> users = userdao.findAll();
        for (User user : users) {
            System.out.println("-----每个用户的信息-------");
            System.out.println(users);
            System.out.println(user.getAccounts());
        }
    }

}
