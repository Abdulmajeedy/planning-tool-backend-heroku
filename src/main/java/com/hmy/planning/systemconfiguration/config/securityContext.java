package com.hmy.planning.systemconfiguration.config;

import com.hmy.planning.systemconfiguration.filter.JwtRequestFilter;
import com.hmy.planning.systemconfiguration.service.JPAUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
public class securityContext {

    @Autowired
    // private UserDetailsService myUserDetailsService;
    private JPAUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    CorsConfigurationSource CorsConfiguration;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().disable().csrf().disable()
                .authorizeRequests().antMatchers("/v1/login",
                        "/email/send_text_email")
                .permitAll().anyRequest()
                .authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

}
