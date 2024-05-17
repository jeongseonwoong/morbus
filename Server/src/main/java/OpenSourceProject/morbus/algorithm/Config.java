package OpenSourceProject.morbus.algorithm;

import OpenSourceProject.morbus.repository.JdbcTemplateMemberRepository;
import OpenSourceProject.morbus.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
