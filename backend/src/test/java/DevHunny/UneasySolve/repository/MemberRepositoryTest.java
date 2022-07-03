package DevHunny.UneasySolve.repository;

import DevHunny.UneasySolve.domain.Member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired private EntityManager em;
    @Autowired private MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
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
    public void 회원가입_중복정() throws Exception {
        //given
        Member newMember = new Member();
        newMember.setEmail("test");
        newMember.setPassword("test");
        newMember.setNickname("test");
        newMember.setAddress("test");

        memberRepository.save(newMember);
        em.flush();

        Member duplicateMember = new Member();
        duplicateMember.setEmail("test");
        duplicateMember.setPassword("test");
        duplicateMember.setNickname("test");
        duplicateMember.setAddress("test");


        //when
        RuntimeException error = assertThrows(PersistenceException.class, () -> {
            memberRepository.save(duplicateMember);
            em.flush();
        });
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
    public void 회원찾기_이메일_오류() throws Exception {
        //given
        //No member insert

        //when
        Member findOne = memberRepository.findOneByEmail("Wrong Email");

        //then
        assertThat(findOne).isEqualTo(null);
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