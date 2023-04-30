package com.example.projectbackend.security;
import com.example.projectbackend.filter.JwtRequestFilter;
import com.example.projectbackend.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    private MyUserDetailsService myUserDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(MyUserDetailsService myUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService myUserDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeRequests()

//                                .antMatchers("/**").permitAll()

                //USERS
                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()

                //ACCOUNTS
                .antMatchers(HttpMethod.GET, "/accounts").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/accounts/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/accounts/{id}").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/accounts/{id}").hasAuthority("ADMIN")

                //TICKETS
                .antMatchers(HttpMethod.POST, "/tickets/create").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/tickets/admin").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/tickets").permitAll()
                .antMatchers(HttpMethod.GET, "/tickets/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/tickets/{id}").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/tickets/{id}").hasAuthority("ADMIN")

                //ORDERS
                .antMatchers(HttpMethod.GET, "/orders").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/orders/create").permitAll()
                .antMatchers(HttpMethod.POST, "/orders/create/{accountId}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/orders/{id}").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/orders/{id}").hasAuthority("ADMIN")

                //PRODUCTS
                .antMatchers(HttpMethod.GET, "/products").permitAll()
                .antMatchers(HttpMethod.GET, "/products/{id}").permitAll()

                //UPLOAD/DOWNLOAD
                .antMatchers(HttpMethod.POST, "/upload").permitAll()
                .antMatchers(HttpMethod.POST, "/accounts/0/photo").permitAll()
                .antMatchers(HttpMethod.GET, "/download/{fileName}").permitAll()
                .antMatchers(HttpMethod.GET, "/download/image0.jpeg").permitAll()

                //SENDMAIL
                .antMatchers(HttpMethod.POST, "/sendMail").permitAll()
                .antMatchers(HttpMethod.POST, "/sendMailWithAttachment").permitAll()

                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
