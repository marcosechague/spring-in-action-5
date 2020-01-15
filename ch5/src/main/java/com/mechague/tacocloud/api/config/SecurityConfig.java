package com.mechague.tacocloud.api.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*private DataSource dataSource;

    public SecurityConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }*/

    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    public SecurityConfig(@Qualifier(value = "userRepositoryUserDetailsService")
                          UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    //Configuration to allow access to h2 console
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                //base on SpEL
                /*.antMatchers("/design", "/orders")
                    .access("hasRole('USER')")
                    .antMatchers("/", "/**")
                    .access("permitAll")*/
                .antMatchers("/h2-console/**")
                    .permitAll()
                .antMatchers("/v3/design", "/v3/orders")
                //'ROLE_' is added automatically
                    .hasRole("USER")
                .antMatchers("/", "/**")
                    .permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    //The true is to force the user to the design page after login, even if they were
                    //navigating elsewhere prior to logging in
                    .defaultSuccessUrl("/v3/design", true)
                    .loginProcessingUrl("/authenticate")
                    .usernameParameter("user")
                    .passwordParameter("pwd")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                //Only to allow access to h2-console
                .and()
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                .and()
                    .headers()
                    .frameOptions()
                    .sameOrigin();
    }


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from Users " +
                                "where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from UserAuthorities " +
                                "where username=?");
    }*/

    //For in memory auth
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
                auth.inMemoryAuthentication()
                    .withUser("buzz")
                        //Spring 5 requires storage format, for plain text is {noop}
                        .password("{noop}infinity")
                        .authorities("ROLE_USER")
                    .and()
                    .withUser("woody")
                        .password("{noop}bullseye")
                        .authorities("ROLE_USER");
    }*/
}
