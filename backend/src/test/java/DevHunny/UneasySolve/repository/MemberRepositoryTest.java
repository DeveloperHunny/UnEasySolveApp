package DevHunny.UneasySolve.repository;

import DevHunny.UneasySolve.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired private EntityManager em;
    @Autowired private MemberRepository memberRepository;


//    @AfterEach
//    public void afterEach(){
//        em.clear();
//    }


    @Test
    public void 회원저장() throws Exception {
        //given
        Member newMember = new Member();
        newMember.setEmail("eogns0321@gmail.com");
        newMember.setPassword("abcedscqaadfqA!");
        newMember.setNickname("Hunny");
        newMember.setAddress("경기도 용인시 기흥구 마북로 154번기 16");

        //when
        memberRepository.save(newMember);

        //then
        Member member = em.find(Member.class, newMember.getId());
        assertThat(member.getEmail()).isEqualTo(newMember.getEmail());

    }

    @Test
    public void 회원찾기_이메일() throws Exception {
        //given
        Member newMember = new Member();
        newMember.setEmail("eogns0321@gmail.com");
        newMember.setPassword("abcedscqaadfqA!");
        newMember.setNickname("Hunny");
        newMember.setAddress("경기도 용인시 기흥구 마북로 154번기 16");

        memberRepository.save(newMember);

        //when
        Member findOne = memberRepository.findOneByEmail(newMember.getEmail());

        //then
        assertThat(findOne.getNickname()).isEqualTo(newMember.getNickname());
    }

    @Test
    public void 회원찾기_모두() throws Exception {
        //given
        Member newMember1 = new Member();
        newMember1.setEmail("eogns03211@gmail.com");
        newMember1.setPassword("abcedscqaadfqA!1");
        newMember1.setNickname("Hunny1");
        newMember1.setAddress("경기도 용인시 기흥구 마북로 154번기 161");
        memberRepository.save(newMember1);

        Member newMember2 = new Member();
        newMember2.setEmail("eogns03212@gmail.com");
        newMember2.setPassword("abcedscqaadfqA!2");
        newMember2.setNickname("Hunny2");
        newMember2.setAddress("경기도 용인시 기흥구 마북로 154번기 162");
        memberRepository.save(newMember2);

        Member newMember3 = new Member();
        newMember3.setEmail("eogns03213@gmail.com");
        newMember3.setPassword("abcedscqaadfqA!3");
        newMember3.setNickname("Hunny3");
        newMember3.setAddress("경기도 용인시 기흥구 마북로 154번기 163");
        memberRepository.save(newMember3);

        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(3);
    }



}