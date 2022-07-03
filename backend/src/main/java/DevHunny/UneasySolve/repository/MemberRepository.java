package DevHunny.UneasySolve.repository;

import DevHunny.UneasySolve.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.plaf.metal.MetalMenuBarUI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
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
        try{
            Member queryResult = em.createQuery("select m from Member m where m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return queryResult;
        }
        catch(NoResultException e){
            return null;
        }


    }

    public Member findOneByNickname(String nickname){
        return  em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                .setParameter("nickname" , nickname)
                .getSingleResult();
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //TODO( delete & update 구현은 추후에 하기로 하자)

}
