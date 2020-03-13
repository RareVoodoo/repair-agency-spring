package ua.testing.repairagency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ua.testing.repairagency.service.UserDetailsImp;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;


    @Autowired
    private UserDetailsImp userDetailsImp;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled " +
                        "from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority" +
                        " from authorities where username = ?");
//                .withDefaultSchema()
//                .withUser(User.withUsername("user").password("{noop}123").roles("USER"))
//                .withUser(User.withUsername("admin").password("{noop}123").roles("ADMIN"));

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll().and()
                .authorizeRequests().antMatchers("/registration").permitAll()

                .antMatchers("/user").hasAnyRole("ADMIN","USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/master").hasAnyRole("MASTER", "ADMIN")

                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .successHandler(authenticationSuccessHandler)
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .and().csrf().disable()
                    .headers().frameOptions().disable();
    }

}