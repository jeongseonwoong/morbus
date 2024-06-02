package OpenSourceProject.morbus.repository;

import OpenSourceProject.morbus.VOclass.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

class MemoryMemberRepositoryTest {

    private static Map<Long, Member> store = new HashMap<>();

    @Test
    void save() {
        Member member = new Member();
        member.setId(21L);
        store.put(member.getId(),member);
        Assertions.assertThat(store.get(member.getId())).isEqualTo(member);
    }

    @Test
    void findById() {
        Member member = new Member();
        member.setId(11L);
        store.put(member.getId(),member);
        Assertions.assertThat(store.get(member.getId())).isEqualTo(member);
    }

    @Test
    void findByName() {
        Member member = new Member();
        member.setId(11L);
        member.setName("test");
        store.put(member.getId(),member);
        Assertions.assertThat( store.values().stream().filter(member1 -> member1.getName().equals("test")).findAny().get().getName()).isEqualTo("test");
    }

    @Test
    void findAll() {
        Member member = new Member();
        member.setId(11L);
        member.setName("test");
        store.put(member.getId(),member);
        Member member2 = new Member();
        member2.setId(12L);
        member2.setName("test2");
        store.put(member2.getId(),member2);
        Assertions.assertThat(store.size()).isEqualTo(2);
    }

    @AfterEach
    void clear() {
        store.clear();
    }
}