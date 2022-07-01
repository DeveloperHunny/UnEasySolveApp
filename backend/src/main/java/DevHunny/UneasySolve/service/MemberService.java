package DevHunny.UneasySolve.service;

import DevHunny.UneasySolve.domain.Member;
import DevHunny.UneasySolve.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        validationInput(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validationInput(Member member) {
        //TOOD(추후에 멤버 들어온 것 검증하는 것 추가하기)
    }

    public Member tryLogin(Member member){
        Member findOne = memberRepository.findOneByEmail(member.getEmail());
        if(findOne == null) return null;
        if(findOne.getPassword() != member.getPassword()) return null;

        return findOne;
    }

    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }


}
