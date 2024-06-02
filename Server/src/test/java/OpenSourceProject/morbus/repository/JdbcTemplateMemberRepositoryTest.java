package OpenSourceProject.morbus.repository;

import OpenSourceProject.morbus.VOclass.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

@SpringBootTest
@Transactional
class JdbcTemplateMemberRepositoryTest {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Test
    void save() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Member member = new Member();
        member.setName("abc");
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),"abc" );
        Assertions.assertThat(result.stream().findFirst().get().getName()).isEqualTo(member.getName());
    }

    @Test
    void findById() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Member member = new Member();
        member.setName("cde");
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),member.getId());
        Assertions.assertThat(result.stream().findFirst().get().getName()).isEqualTo(member.getName());
    }

    @Test
    void findByName() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Member member = new Member();
        member.setName("abc");
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),member.getName() );
        Assertions.assertThat(result.stream().findFirst().get().getName()).isEqualTo(member.getName());
    }

    @Test
    void findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Member member = new Member();
        member.setName("cde");
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),"cde" );
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}