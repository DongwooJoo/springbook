package org.example;

import org.user.dao.UserDao;
import org.user.domain.User;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("ju");
        user.setName("동우");
        user.setPassword("dongwoo");

        dao.add(user);

        System.out.println(user.getId() + "등록 완료");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회 완료");

    }
}