package com.example.demo.config;

import com.example.demo.config.jwt.JWTAuthenticationFilter;
import com.example.demo.config.jwt.JwtAuthenticationEntryPoint;
import com.example.demo.config.jwt.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/register/**")
                .permitAll()
                //test api login
                .antMatchers("/login").permitAll()
                // add filter to handle exception
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
//                 let spring security doesnt create or use any session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //
                .and()
                .authorizeRequests()
                .antMatchers("/unauthorised").permitAll()
                .antMatchers("/list-user").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/product/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/category/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/unauthorised")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .loginProcessingUrl("/doLogin").successForwardUrl("/goHome")
                .failureUrl("/loginFailed")
                .and()
                .logout().logoutUrl("/doLogOut").logoutSuccessUrl("/login")
                .permitAll();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }

    @Autowired
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
