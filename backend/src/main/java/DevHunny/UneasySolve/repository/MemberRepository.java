package DevHunny.UneasySolve.repository;

import DevHunny.UneasySolve.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.plaf.metal.MetalMenuBarUI;
import java.util.List;

@Repository
public class MemberRepository {

    private final EntityManager em;

    @Autowired
    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Member member){
        em.persist(member);
    }

    public Member findOneById(Long id){
        return em.find(Member.class, id);
    }

    public Member findOneByEmail(String email){
        return (Member) em.createQuery("select m from Member m where m.email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    public Member findOneByNickname(String nickname){
        return (Member) em.createQuery("select m from Member m where m.nickname = :nickname")
                .setParameter("nickname" , nickname)
                .getSingleResult();
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //TODO( delete & update 구현은 추후에 하기로 하자)

}
