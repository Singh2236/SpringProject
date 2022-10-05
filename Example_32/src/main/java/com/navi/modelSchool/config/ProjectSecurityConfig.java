package com.navi.modelSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectSecurityConfig {
    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/saveMsg")
                .ignoringAntMatchers("/h2-console/**")
                .and()
                .authorizeRequests()
                .mvcMatchers("/dashboard").authenticated() //always secure the dashboard
                .mvcMatchers("/home").permitAll()
                .mvcMatchers("/holidays/**").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/saveMsg").permitAll()
                .mvcMatchers("/courses").permitAll()
                .mvcMatchers("/about").permitAll()
                .mvcMatchers("/login").permitAll() //login page should be seen by all
                .and().formLogin().loginPage("/login")//mapping to your own login page
                .defaultSuccessUrl("/dashboard")// fwd to dashboard after successful logging
                .failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true).permitAll()


                .and().httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails navi = User.withDefaultPasswordEncoder()
                .username("navi")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(navi, admin);
    }


   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("12345").roles("USER")
                .and()
                .withUser("admin").password("54321").roles("USER", "ADMIN")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }*/

}
