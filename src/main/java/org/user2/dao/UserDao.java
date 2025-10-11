package org.user2.dao;

import org.user.domain.User;

import java.sql.*;

// 클래스 계층구조를 통해 관심사 분리(1. DB 연결 방법, 2. SQL 작성, 파라미터 바인딩, 쿼리 실행 등)
public abstract class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    // 추상 메서드로 선언하여 하위 클래스에서 구현 -> 템플릿 메서드 패턴
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    // 하위 클래스에서 구현 -> 팩토리 메서드 패턴
    public class NUserDao extends UserDao {
        public Connection getConnection() throws ClassNotFoundException, SQLException {
            // N사에서는 다르게 DB 연결 가능
            Class.forName("org.mariadb.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/springbook", "spring", "book");
            return c;
        }
    }

    public class DUserDao extends UserDao {
        public Connection getConnection() throws ClassNotFoundException, SQLException {
            // D사에서는 다르게 DB 연결 가능
            Class.forName("org.h2.Driver");
            Connection c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            return c;
        }
    }
}
