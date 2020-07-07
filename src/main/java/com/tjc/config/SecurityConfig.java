package com.tjc.config;

import com.tjc.component.KaptchaFilter;
import com.tjc.mapper.UserMapper;
import com.tjc.pojo.SecurityUser;
import com.tjc.pojo.User;
import com.tjc.service.CustomUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: TJC
 * @Date: 2020/6/16 12:53
 * @description: TODO
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 后也所有人可访问, 功能页只有对应权限能访问
        // 请求授权规则
        /*http.authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/toLogin").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/toReg").permitAll()
                .antMatchers("/register").permitAll();*/
        // 没有权限默认会到登录页面
//        http.formLogin().loginPage("/user/login.html");
        http.formLogin().loginPage("/user/toLogin")
                .loginProcessingUrl("/user/login")
                .usernameParameter("email")
                .passwordParameter("password")
                /*.successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        User user = (User) authentication.getPrincipal();
                        System.out.println("onAuthenticationSuccess ==> ");
                        System.out.println(user);
                        httpServletRequest.getSession().setAttribute("loginUser", user);
                    }
                })*/.successForwardUrl("/user/loginSuccess")
                .failureForwardUrl("/user/loginFailure")
                .and()
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/verification").permitAll()
                .antMatchers("/user/toLogin").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/toReg").permitAll()
                .antMatchers("/user/emailVerify").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/layui/**").permitAll()
                .antMatchers("/mods/**").permitAll()
                .anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .logout().logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("remove").invalidateHttpSession(true)
                .and()
                .csrf().disable();
        //注销
//        http.csrf().disable(); //关闭csrf功能
//        http.logout().logoutSuccessUrl("/index");

        //记住我
//        http.rememberMe();
        http.addFilterBefore(new KaptchaFilter("/toLogin", "/toLogin?error"), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //密码加密
        return new BCryptPasswordEncoder();
    }


}
