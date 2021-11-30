package br.com.allangf.BlogAPI.rest.config;

import br.com.allangf.BlogAPI.rest.Service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/tag/**")
                        .hasRole("USER")
                    .antMatchers(HttpMethod.POST, "/api/post/**")
                        .hasRole("USER")
                    .antMatchers(HttpMethod.GET, "/api/tag/**")
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "/api/post/**")
                        .permitAll()
                    .antMatchers("/api/user/**")
                        .permitAll()
                .antMatchers().authenticated()
                .and()
                .httpBasic();
    }

}
