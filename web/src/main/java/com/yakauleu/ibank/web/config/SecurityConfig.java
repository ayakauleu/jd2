package com.yakauleu.ibank.web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers("/signup")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .antMatchers("/payments")
                    .hasAnyRole("ADMIN", "SUPPORTER", "ACCOUNTER")
                .antMatchers("/board")
                    .hasRole("CLIENT")
                    .and()
                .formLogin()
                    .loginPage("/my-login")
                    .permitAll(true)
                    .defaultSuccessUrl("/board")
                .and()
                .logout()
                    .logoutSuccessUrl("/my-login")
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                    .and()
                .csrf().disable();


        /*       http.
                authorizeRequests()
                .antMatchers("/admin")
                    .hasAnyAuthority("ADMIN", "ADMIN2")
                .antMatchers("/admin2")
                    .hasAnyAuthority("ADMIN2")
                .antMatchers("/login")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                .defaultSuccessUrl("/welcome", true)
//                .and()
//                    .httpBasic()
                .and()
                    .logout();
//                .and()
//                    .csrf().disable();
        http
                .userDetailsService(userDetailsService);  */
    }
}