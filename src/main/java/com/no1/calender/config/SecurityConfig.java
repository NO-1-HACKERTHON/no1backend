package com.no1.calender.config;

import com.no1.calender.jwt.JwtAccessDeniedHandler;
import com.no1.calender.jwt.JwtAuthenticationEntryPoint;
import com.no1.calender.jwt.JwtSecurityConfig;
import com.no1.calender.jwt.TokenProvider;
import io.swagger.models.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                        ,"/error"
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                // swagger 401 issue
                .antMatchers("/").anonymous()
                .antMatchers("/swagger-ui-html").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/v2/**").anonymous()
                .antMatchers("/csrf").anonymous()

                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/signup").permitAll()

                // .antMatchers(HttpMethod.POST, "").authenticate()
                .antMatchers(String.valueOf(HttpMethod.POST), "/challenge/plants").authenticated()
                .antMatchers(String.valueOf(HttpMethod.POST), "/challenge/plants/**").authenticated()

                .anyRequest().permitAll()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
