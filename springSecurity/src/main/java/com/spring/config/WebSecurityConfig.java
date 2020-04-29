package com.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    // 定义用户信息服务（查询用户信息）。查询数据库或内存方式。
    @Bean
    public UserDetailsService userDetailsService() {
        // 基于内存的方式。
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        inMemoryUserDetailsManager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return inMemoryUserDetailsManager;
    }

    // 密码编码器。（密码比对方式）。
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 不加密。
        return NoOpPasswordEncoder.getInstance();
    }

    // 安全拦截机制。（关键）。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()

                // 拦截实现授权。
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")

                .antMatchers("/r/**").authenticated()// 此路径 /r/** 要求认证。
                .anyRequest().permitAll()// 除了 /r/**，其他放行。
                .and()
                .formLogin()// 允许表单登录。
                .successForwardUrl("/login-success");// 自定义登录成功的页面地址。
    }

}
