package com.christos.springtodo.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //Create user - test@test.com / 123456
    //Using in memory authentication to start with
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("test@test.com").password("123456")
                .roles("USER", "ADMIN");
    }

    //Create a Login form.
    // for /login permit anybody. Otherwise, you need to have a role of user.
    // Otherwise, redirect to /login
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/login", "/h2/**")
                .permitAll()
                .antMatchers("/", "/*todo*/**")
                .access("hasRole('USER')")
                .and()
                .formLogin();

        // Disabled for H2 database to work
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

}
