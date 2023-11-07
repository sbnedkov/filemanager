package com.example.filemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
                    .hasRole("USER"))
            .formLogin((form) ->
                form
                    .loginPage("/login")
                    .permitAll()
            )
            .logout((logout) -> logout.permitAll());

		return http.build();
	}

    @Autowired
    public void configAuthentication (AuthenticationManagerBuilder auth, UserRepository userRepo) throws Exception {
	 	UserDetailsService userDetailsService = new UserDetailsService() {
            public UserDetails loadUserByUsername (String username) {
                UserEntity aUser = new UserEntity(username);
                ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("username", match -> match.exact());
                UserEntity theUser = userRepo.findOne(Example.of(aUser, matcher)).get();
                return User.withUsername(username)
                    .disabled(!theUser.getEnabled())
                    .roles("USER")
                    .password(theUser.getPassword())
                    .build();
            }
        };

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
}
