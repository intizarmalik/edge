package com.etip.ezugi.app.security.web;

import com.etip.ezugi.app.constants.ApiEndPointsConstants;
import com.etip.ezugi.app.security.jwt.AuthRequestFilter;
import com.etip.ezugi.app.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfigs extends WebSecurityConfigurerAdapter {

    @Autowired
    private final AuthRequestFilter unauthorizedHandler;

    public WebSecurityConfigs(AuthRequestFilter unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requiresChannel().anyRequest().requiresSecure();
        http.cors().and().csrf().disable();
      //  http.headers().disable();

        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().
                antMatchers(ApiEndPointsConstants.EZUGI_INTEGRATION + "**")
                .permitAll();

        http.authorizeRequests().anyRequest().authenticated();
    }


}
