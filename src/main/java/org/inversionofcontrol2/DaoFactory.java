package org.inversionofcontrol2;

import org.seperateresponsibilityofrelationship.ConnectionMaker;
import org.seperateresponsibilityofrelationship.DConnectionMaker;
import org.seperateresponsibilityofrelationship.UserDao;

public class DaoFactory {
    public UserDao userDao () {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
}
