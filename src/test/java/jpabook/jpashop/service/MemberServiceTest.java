package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryV1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberRepositoryV1 memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입")
    public void join(){
        //Given
        Member member = new Member();
        member.setName("Kim");

        Long saveId = memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    @DisplayName("동일한 회원 가입 시 에러 발생하는지 검증")
    public void join_validation(){
        //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //When
        memberService.join(member1);

        //Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

        // 메시지도 같이 확인 가능
        Assertions.assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
}
}