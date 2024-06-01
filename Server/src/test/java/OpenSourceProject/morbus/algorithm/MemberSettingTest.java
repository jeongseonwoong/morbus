package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.repository.MemberRepository;
import OpenSourceProject.morbus.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberSettingTest {

    MemberRepository memberRepository = new MemoryMemberRepository();
    Member member=new Member();

    MemberSettingTest(){
        member.setId(12L);
        member.setName("abc");
        memberRepository.save(member);
        memberRepository.findById(12L);
    }

    @Test
    void findMemberById() {

        Assertions.assertThat(memberRepository.findById(member.getId()).get().getName()).isEqualTo(member.getName());
    }

    @Test
    void findName() {
        Assertions.assertThat(memberRepository.findByName(member.getName()).get().getName()).isEqualTo(member.getName());
    }

    @Test
    void findMember()
    {
        Assertions.assertThat(memberRepository.findAll()).isNotEmpty();
    }
}