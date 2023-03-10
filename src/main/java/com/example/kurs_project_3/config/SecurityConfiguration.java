package com.example.kurs_project_3.config;

import com.example.kurs_project_3.buisnesslayer.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
				.antMatchers("/admin/rights/*","/admin/rights/", "/admin/*","/admin/user/*","/admin/user/**","/admin/user/***")
				.hasAuthority("ROLE_ADMIN")
                .antMatchers("/department","/department/delete/*","/department/update",
                        "/employee","/employee/delete/*","/employee/update",
                        "/employee_speciality","/employee_speciality/delete/employee/{emplId}/speciality/{specId}",
                        "/post","/post/delete/*","/post/update",
                        "/promotion","/promotion/delete/*","/promotion/update",
                        "/qualification","/qualification/delete/*","/qualification/update","/qualification/SET_TODAY_IF_QUALIFICATION_WAS_EXPIRED",
                        "/speciality","/speciality/delete/*","/speciality/update"
                )
                .hasAnyRole("EMPLOYEE","ADMIN")
                .antMatchers(
                        "/registration**",
                        "/js/**",
                        "/css/**",
                        "/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessdenied").and().build();

    }

}
