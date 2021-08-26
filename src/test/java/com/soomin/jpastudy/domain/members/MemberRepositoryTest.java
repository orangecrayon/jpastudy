package com.soomin.jpastudy.domain.members;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @After
    public void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    public void MemberTest() {
        try {
            Member member = Member.builder()
                    .id("ABC")
                    .age(10)
                    .username("soomin_kang")
                    .build();
            memberRepository.save(member);

            List<Member> memberList = memberRepository.findAll();
            System.out.println("member = " + memberList.get(0));
            assertThat(memberList.get(0).getId()).isEqualTo("ABC");


            member.setAge(20);
            Optional<Member> res = memberRepository.findById("ABC");

            System.out.println("member = " + res);
            assertThat(res.get().getAge()).isEqualTo(20);

        } catch(Exception e) {
        } finally {
        }
    }

    @Test
    public void ManyToOneTest() {
        Team team = Team.builder()
                .id("team1")
                .name("팀1")
                .build();
        // Many-to-One 에서 One이 되는 객체를 항상 먼저 persist context에 넣어야함
        // 순서가 틀리면 ConstraintViolationException 발생
        teamRepository.save(team);

        Member member1 = Member.builder()
                .id("member1")
                .age(10)
                .team(team) // many-to-one 관계
                .username("soomin_kang")
                .build();
        Member member2 = Member.builder()
                .id("member2")
                .age(20)
                .team(team)
                .username("test")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        Optional<Member> res1 = memberRepository.findById("member1");
        assertThat(res1.get().getTeam().getId()).isEqualTo("team1");

        Optional<Member> res2 = memberRepository.findById("member2");
        assertThat(res1.get().getTeam().getName()).isEqualTo(res2.get().getTeam().getName());

        // 두 member 객체(res1, res2)의 getTeam() 결과가 같은 레퍼런스가 아님. 왜??
        // EntityManager에 이미 저장되어 있는 객제를 반환할 것 같은데 왜 레퍼런스가 다르지?
//        assertThat(res1.get().getTeam()).isEqualTo(res2.get().getTeam());

        // team 업데이트 테스트
        Team team2 = Team.builder()
                .id("team2").name("팀2").build();

        res2.get().setTeam(team2);
    }
}
