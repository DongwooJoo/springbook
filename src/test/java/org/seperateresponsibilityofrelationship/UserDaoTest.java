package org.seperateresponsibilityofrelationship;

import org.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDaoTest test = new UserDaoTest();
        test.testUserDaoDconnectionMaker();
        test.testUserDaoNconnectionMaker();
    }

    public void testUserDaoDconnectionMaker() throws ClassNotFoundException, SQLException {
        // UserDao가 사용할 ConnectionMaker 구현 클래스를 결정하고 오브젝트를 생성 -> UserDaoTest의 책임
        ConnectionMaker connectionMaker = new DConnectionMaker();
        // UserDao 오브젝트 생성 시, 사용한 ConnectionMaker 타입의 오브젝트 제공 -> UserDao의 책임
        // 이를 통해 두 오브젝트 사이의 의존관계 설정효과
        UserDao dao = new UserDao(connectionMaker);

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

    public void testUserDaoNconnectionMaker() throws ClassNotFoundException, SQLException {
        // N사의 독자적인 DB 연결
        // ConnectionMaker connectionMaker = new NConnectionMaker();
        // UserDao dao = new UserDao(connectionMaker);
    }

}
// UserDaoTest: UserDao의 클라이언트
// 책임: UserDao와 ConnectionMaker 구현 클래스와의 런타임 오브젝트 의존관계를 설정하는 책임을 담당
// 테스트를 할 오브젝트의 의존관계를 설정하고, 기존 자신의 책임인 UserDao에 대한 테스트를 수행

// 관심사 분리 방법
// 1. 인터페이스를 도입하고 클라이언트의 도움을 얻는 방법
// 2. 상속을 이용하는 방법
// 인터페이스가 상속보다 유연함.

// -> 다른 DAO 클래스에도 ConnectionMaker 인터페이스를 구현하여 사용할 수 있음
// -> DAO가 많아져도 DB 접속방법에 대한 관심은 ConnectionMaker 인터페이스 한 군데에 집중할 수 있음 -> DB 접속방법을 변경할 때에도 DB 접속방법 구현체 1곳만 수정하면 됨
// -> 다른 DAO 클래스에서 상속을 사용할 수 없음 -> DAO가 확장할 수 없음