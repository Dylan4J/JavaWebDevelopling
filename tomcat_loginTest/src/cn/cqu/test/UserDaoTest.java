package cn.cqu.test;

import cn.cqu.dao.UserDao;
import cn.cqu.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User user = new User();
        user.setUsername("典韦");
        user.setPassword("123");

        UserDao userDao = new UserDao();
        User returnUser = userDao.login(user);
        System.out.println(returnUser);
    }
}
