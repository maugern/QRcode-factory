package config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery(
                "select alias,password from users where alias=?");
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

        http.authorizeRequests()
        .antMatchers("/users/**").access("hasRole('ROLE_USER')").and()
        .formLogin().loginPage("login").failureUrl("/login?error")
        .usernameParameter("username").passwordParameter("password").and()
        .logout().logoutUrl("/login?logout").and()
        .exceptionHandling().accessDeniedPage("/401").and()
        .csrf();
    }
}
