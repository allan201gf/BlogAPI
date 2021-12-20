package br.com.allangf.BlogAPI.rest.config;

import br.com.allangf.BlogAPI.rest.Service.impl.UserDetailsServiceImpl;
import br.com.allangf.BlogAPI.rest.config.jwt.JwtAuthFilter;
import br.com.allangf.BlogAPI.rest.config.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, userDetailsService);
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
                // Endpoints free
                .antMatchers("/api/tag/**",
                        "/api/user/v1/getloggeduser")
                .hasAnyRole(Roles.USER, Roles.ADMIN)
                .antMatchers(HttpMethod.GET, "/api/post/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/v1", "/api/user/v1/login")
                .permitAll()
                // User and Admin
                .antMatchers(HttpMethod.POST, "/api/post/**")
                .hasAnyRole(Roles.USER, Roles.ADMIN)
                .antMatchers(HttpMethod.PATCH, "/api/post/**")
                .hasAnyRole(Roles.USER, Roles.ADMIN)
                .antMatchers(HttpMethod.DELETE, "/api/post/**")
                .hasAnyRole(Roles.USER, Roles.ADMIN)
                .antMatchers(HttpMethod.DELETE, "/api/user/**")
                .hasAnyRole(Roles.USER, Roles.ADMIN)
                // Swagger and e-mail
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/api/email/v1")
                .permitAll()
                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
