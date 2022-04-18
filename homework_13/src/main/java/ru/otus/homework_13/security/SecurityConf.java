package ru.otus.homework_13.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests()
                .and()
                .authorizeRequests()
                //.antMatchers(HttpMethod.POST,"/books/add", "/books/remove**", "/books/edit**", "/books/remove{id}")
                .antMatchers("/books/add**", "/books/remove**", "/books/edit**", "/books/remove{id}")
                .hasAnyRole("ADMIN")
                //.hasRole("ADMIN")
                //.and().authorizeRequests().antMatchers("/**").denyAll()
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/")
                .and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID").permitAll()
                .and().formLogin();
    }
}
