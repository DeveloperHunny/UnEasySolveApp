package DevHunny.UneasySolve.service;

import DevHunny.UneasySolve.domain.Member;
import DevHunny.UneasySolve.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        validationInput(member);
        try{
            memberRepository.save(member);
            return member.getId();
        }
        catch (PersistenceException e){
            System.out.println("THIS IS ERROR");
            return null; }
    }

    private void validationInput(Member member) {
        //TOOD(추후에 멤버 들어온 것 검증하는 것 추가하기)
    }

    public Member tryLogin(Member member){
        Member findOne = memberRepository.findOneByEmail(member.getEmail());

        if(findOne == null) return null;
        if(!findOne.getPassword().equals(member.getPassword())) return null;

        return findOne;
    }

    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }


}
