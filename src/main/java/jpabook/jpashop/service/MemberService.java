package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //jpa의 모든 데이터 변경이나 로직들은 트랜젝션 안에서 이루어져야 한다.
//lombok @AllArgsConstructor
@RequiredArgsConstructor //final이 있는 필드만 생성자를 만들어줌.
public class MemberService {

    private final MemberRepository memberRepository;
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository=memberRepository;
//    }

    /**
     * 회원가입
     */
    @Transactional //굳이 따로 적은 것은 readOnly=false로 됨
    public Long join(Member member){
        validateDuplicateMemebr(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMemebr(Member member){
        List<Member> findMembers =memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    //@Transactional(readOnly = true) //트랜잭션에서 조회의 기능은 readOnly=true로 하면 성능이 더 올라감
    //위에서 설정했기 때문에 따로 적지 않아도 됨.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //단건 조회
    //@Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
