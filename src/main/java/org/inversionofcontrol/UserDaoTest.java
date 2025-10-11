package org.inversionofcontrol;

import java.sql.SQLException;

import org.seperateresponsibilityofrelationship.UserDao;
import org.user.domain.User;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.userDao();
        User user = new User();
        user.setId("ju");
        user.setName("동우");
        user.setPassword("dongwoo");
        userDao.add(user);
    }
}
