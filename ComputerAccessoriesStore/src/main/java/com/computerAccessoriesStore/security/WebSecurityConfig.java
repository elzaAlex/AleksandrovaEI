package com.computerAccessoriesStore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/","/forgotPassword", "/signIn", "/signUp", "/css/**", "/fonts/**", "/img/**", "/scripts/**")
                .permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/act/**").access("hasRole('ADMIN')")
                .antMatchers("/buyer/**").access("hasRole('ADMIN') or hasRole('BUYER')")
                .antMatchers("/seller/**").access("hasRole('ADMIN') or hasRole('SELLER')")
                .antMatchers("/product/**").access("hasRole('BUYER') or hasRole('ADMIN')")
                .antMatchers("/home").access( "hasRole('SELLER') or hasRole('ADMIN') or hasRole('BUYER')")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/signIn")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, true from account where username=?")
                .authoritiesByUsernameQuery("select username, role from account where username=?");
    }
}