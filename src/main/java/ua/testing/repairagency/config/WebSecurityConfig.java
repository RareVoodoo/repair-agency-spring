package ua.testing.repairagency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ua.testing.repairagency.util.Constants;

import javax.sql.DataSource;

@Component
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private final DataSource dataSource;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler, DataSource dataSource) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(Constants.USERS_BY_USERNAME_QUERY)
                .authoritiesByUsernameQuery(Constants.AUTHORITIES_BY_USERNAME_QUERY)
                .passwordEncoder(bcryptPasswordEncoder());
    }


    @Bean

    public PasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console/**").hasRole("ADMIN").and()
                .authorizeRequests().antMatchers("/registration").permitAll().and()
                .authorizeRequests().antMatchers("/resources/**").permitAll()

                .antMatchers("/user").hasAnyRole("ADMIN","USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/master").hasAnyRole("MASTER", "ADMIN")


                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(authenticationSuccessHandler)
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .and().csrf().disable()
                    .headers().frameOptions().disable();
    }


}
