package jpabook.jpashop.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;
    //스프링이 엔티티 매니저를 만들어서 인젝션을 해줌

    public void save(Member member){
        //영속성 컨텍스트에 member엔티티를 추가
        em.persist(member);
    }

    public Member findOne(Long id){
        //JPA에 단건 조회 (타입, pk)
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        //jpql -> sql과 비슷하지만 대상이 프로그램이 아니라 앤티티
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        //sql은 테이블을 대상으로 쿼리를 하는데,
        // Jpql 엔티티 객체를 대상으로 쿼리를 함.
    }

    public List<Member> findByName(String name) {
        //파라미터 바인딩해서 특정 회원의 이름에 대해서 찾는다.
        return em.createQuery("select m from m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();

    }
}
