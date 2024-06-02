package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.VOclass.Member;
import OpenSourceProject.morbus.algorithm.MemberSetting;
import OpenSourceProject.morbus.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberSetting memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member); //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    @Test
    public void 모두_찾아지나(){
        Member member = new Member();
        member.setName("hello");
        Member member2 = new Member();
        member2.setName("hello2");
        memberService.join(member);
        memberService.join(member2);
        //When
        //Then
        memberService.findMember();
        Assertions.assertThat(memberService.findMember().stream().filter(member1 -> member1.getName().equals("hello")).findAny().get().getName()).isEqualTo("hello");
    }

    @Test
    public void 하나씩_찾아지나(){
        Member member = new Member();
        member.setName("hello");
        Member member2 = new Member();
        member2.setName("hello2");
        memberService.join(member);
        memberService.join(member2);

        Assertions.assertThat(memberService.findName("hello").get().getName()).isEqualTo(member.getName());
        Assertions.assertThat(memberService.findMemberById(member2.getId()).get().getId()).isEqualTo(member2.getId());
    }
}