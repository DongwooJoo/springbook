package org.inversionofcontrol2;

import org.inversionofcontrol2.DaoFactory;
import org.seperateresponsibilityofrelationship.UserDao;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao userDao = new DaoFactory().userDao();
    }

}
