package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.repository.JdbcTemplateMemberRepository;
import OpenSourceProject.morbus.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class Config {

    private final DataSource dataSource;


    public Config(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public MemberSetting memberSetting() {
        return new MemberSetting(memberRepository());
    }

    @Primary
    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
