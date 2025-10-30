package com.tetz.jpa.member.repository;


import com.tetz.jpa.jpashop.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveId);

        // then
        Assertions.assertEquals(member.getId(), findMember.getId());
        Assertions.assertEquals(member.getUsername(), findMember.getUsername());
        Assertions.assertEquals(findMember, member); // 같은 Transaction 에서는 같은 엔티티로 판단
    }
}