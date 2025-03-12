package me.yeochan.springbootdeveloper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Sql("/insert-members.sql") // test 실행 전에 sql 쿼리를 실행할 수 있다.
    @Test
    void getAllMembers() {
        // when
        List<Member> members = memberRepository.findAll();
        // then
        assertThat(members.size()).isEqualTo(3);
    }
}