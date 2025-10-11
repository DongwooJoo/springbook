package org.inversionofcontrol;

import org.seperateresponsibilityofrelationship.DConnectionMaker;
import org.seperateresponsibilityofrelationship.ConnectionMaker;
import org.seperateresponsibilityofrelationship.UserDao;

public class DaoFactory {
    // 팩토리의 메소드는 UserDao를 어떻게 생성할지에 대한 책임을 갖는다.
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
    
}
