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
        //jpql -> sql과 비슷하지만 대상이 테이블이 아니라 앤티티
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        //Member.class는 반환할 엔티티 타입을 지정
        //getResultList()를 호출하여 쿼리를 실행하고 결과를 가져옴
    }

    public List<Member> findByName(String name) {
        //파라미터 바인딩해서 특정 회원의 이름에 대해서 찾는다.
        return em.createQuery("select m from m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        //:name은 파라미터 바인딩을 나타내며, 나중에 이를 실제 값으로 설정
        //setParameter("name", name)을 사용하여 :name에 파라미터를 바인딩. 이를 통해 특정 이름을 가진 회원을 찾음
        //getResultList()를 호출하여 쿼리를 실행하고 결과를 가져옴.

    }
}
