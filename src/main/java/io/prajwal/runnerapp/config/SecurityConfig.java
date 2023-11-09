package io.prajwal.runnerapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired CustomUserDetialsService customUserDetialsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login", "/register", "/clubs", "/css/**", "/js/**").permitAll();
            auth.anyRequest().authenticated();
        });

        http.userDetailsService(customUserDetialsService);

        http.formLogin(form -> {
           form.loginPage("/login");
           form.defaultSuccessUrl("/clubs");
           form.loginProcessingUrl("/login");
           form.failureUrl("/login?error=true");
           form.permitAll();
        });

        http.logout(logout -> {
           logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
        });
        return http.build();
    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}