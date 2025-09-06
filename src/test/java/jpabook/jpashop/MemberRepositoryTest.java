package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false) // 테스트에서 실제 DB에 저장되어있는지 확인하기 위해 false 설정
    void testmember() {
        Member member = new Member();
        member.setUsername("memberA");
        Long savedId = memberRepository.save(member);

        Member findmember = memberRepository.findById(savedId);

        Assertions.assertThat(findmember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findmember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findmember).isEqualTo(member);


    }

}