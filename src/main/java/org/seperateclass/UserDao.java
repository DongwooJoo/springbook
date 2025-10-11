package org.seperateclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.user.domain.User;

// 1.3.1 클래스의 분리
public class UserDao {
    private SimpleConnectionMaker connectionMaker;

    // 문제: N사, D사에서 각각 다른 DB 연결 방법을 사용하기 때문에 코드 수정이 필요함
    public UserDao(SimpleConnectionMaker connectionMaker) {
        connectionMaker = new SimpleConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }
    
    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();

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
}
