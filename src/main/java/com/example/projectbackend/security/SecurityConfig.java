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

                //USERS
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users").permitAll()

                .antMatchers(HttpMethod.GET, "/users/{username}").permitAll()

                .antMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.DELETE, "/users/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/users/{id}").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasAuthority("ADMIN")


                //ACCOUNTS
                .antMatchers(HttpMethod.POST, "/accounts").permitAll()
                .antMatchers(HttpMethod.POST, "/accounts/**").permitAll()
                .antMatchers(HttpMethod.GET, "/accounts").permitAll()
                .antMatchers(HttpMethod.GET, "/accounts/{id}").permitAll()

                .antMatchers(HttpMethod.GET, "/accounts/**").permitAll()
                .antMatchers(HttpMethod.GET, "/accounts/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/accounts/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/accounts/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.DELETE, "/accounts/{id}").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/accounts/{id}").hasAuthority("ADMIN")


                //USERACCOUNTS
                .antMatchers(HttpMethod.POST, "users/accounts").permitAll()
                .antMatchers(HttpMethod.POST, "users/accounts/**").permitAll()


                //PRODUCTS
                .antMatchers(HttpMethod.GET, "/products").permitAll()
                .antMatchers(HttpMethod.GET, "/products/{id}").permitAll()


                //TICKETS
                .antMatchers(HttpMethod.POST, "/tickets").permitAll()
                .antMatchers(HttpMethod.POST, "/tickets/**").permitAll()
                .antMatchers(HttpMethod.GET, "/tickets").permitAll()
                .antMatchers(HttpMethod.GET, "/tickets/{id}").permitAll()

                .antMatchers(HttpMethod.GET, "/tickets/**").permitAll()
                .antMatchers(HttpMethod.POST, "/tickets").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/tickets/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/tickets/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/tickets/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.DELETE, "/tickets/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/tickets/{id}").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/tickets/{id}").hasAuthority("ADMIN")


                //ORDERS
                .antMatchers(HttpMethod.POST, "/orders").permitAll()
                .antMatchers(HttpMethod.POST, "/orders/**").permitAll()
                .antMatchers(HttpMethod.GET, "/orders").permitAll()
                .antMatchers(HttpMethod.GET, "/orders/{id}").permitAll()

                .antMatchers(HttpMethod.GET, "/orders/**").permitAll()
                .antMatchers(HttpMethod.GET, "/orders/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/orders/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/orders/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.DELETE, "/orders/{id}").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/orders/{id}").hasAuthority("ADMIN")


                .antMatchers(HttpMethod.POST,"/upload").permitAll()
                .antMatchers(HttpMethod.GET,"/download/{fileName}").permitAll()
                .antMatchers(HttpMethod.POST,"/accounts/{id}/photo").permitAll()


//                .antMatchers("/sendMail").hasAuthority("ADMIN")
                .antMatchers("/sendMail").permitAll()
//                .antMatchers("/sendMailWithAttachment").hasAuthority("ADMIN")
                .antMatchers("/sendMailWithAttachment").permitAll()

                ////
                .antMatchers("/administrator").hasAuthority("ADMIN")
//                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("USER", "ADMIN")
                ///

                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .antMatchers(HttpMethod.GET,"/authenticated").authenticated()
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()

                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
