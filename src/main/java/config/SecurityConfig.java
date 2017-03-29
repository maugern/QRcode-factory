package config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class is the equivalent of spring-security.xml file 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery(
            "select alias, passwdHash from users where alias=?")
        .authoritiesByUsernameQuery(
            "select users.alias, user_roles.role from user_roles, users " +
            "where users.id = user_roles.id AND users.alias=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')").and()
        .formLogin().loginPage("/login").failureUrl("/login?error")
        .usernameParameter("username").passwordParameter("password").and()
        .logout().logoutSuccessUrl("/login?logout").and()
        .exceptionHandling().accessDeniedPage("/403").and()
        .csrf();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    
}
