package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.VOclass.Member;
import OpenSourceProject.morbus.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class MemberSetting {
    private final MemberRepository memberRepository;



    MemberSetting(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
        memberRepository.save(member);
        return member.getId();
    }


    public List<Member> findMember()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }


    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
