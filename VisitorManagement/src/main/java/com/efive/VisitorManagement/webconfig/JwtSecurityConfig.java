package com.efive.VisitorManagement.webconfig;

import java.util.Collections;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.efive.VisitorManagement.entity.Usermaster;
import com.efive.VisitorManagement.security.JwtAuthenticationEntryPoint;
import com.efive.VisitorManagement.security.JwtAuthenticationProvider;
import com.efive.VisitorManagement.security.JwtAuthenticationTokenFilter;
import com.efive.VisitorManagement.security.JwtSuccessHandler;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	Usermaster u=new Usermaster();
    	String role=u.getUsertype();    	
    	
        http.csrf().disable()
                .authorizeRequests().antMatchers("/visitorManagement/**").authenticated()
                //"+role+"
              //  .antMatchers("/visitorManagement/visitor").access("hasRole.equals("+role+")")
              //  .antMatchers("/visitorManagement/visitor").access("hasRole("+role+")")
               // .antMatchers("/visitorManagement/visitor/**").access("hasRole("+role+")")
               // .antMatchers("/visitorManagement/usermaster/**").access("hasRole("+role+") and hasRole("+role+")")
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }
}