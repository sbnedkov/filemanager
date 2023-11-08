package com.example.filemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        XorCsrfTokenRequestAttributeHandler delegate = new XorCsrfTokenRequestAttributeHandler();
        delegate.setCsrfRequestAttributeName("_csrf");
        CsrfTokenRequestHandler requestHandler = delegate::handle;

        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers(AntPathRequestMatcher.antMatcher("/built/login.*"))
                        .permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
                        .hasRole("USER"))
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .csrf((csrf) ->
                csrf.csrfTokenRepository(tokenRepository)
                    .csrfTokenRequestHandler(requestHandler))
            .formLogin((form) ->
                form.loginPage("/login")
                    .permitAll()
            )
            .logout((logout) ->
                logout.permitAll());

		return http.build();
	}

    @Autowired
    public void configAuthentication (AuthenticationManagerBuilder auth, UserRepository userRepo) throws Exception {
	 	UserDetailsService userDetailsService = new UserDetailsService() {
            public UserDetails loadUserByUsername (String username) {
                com.example.filemanager.User aUser = new com.example.filemanager.User(username);
                ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("username", match -> match.exact());
                com.example.filemanager.User theUser = userRepo.findOne(Example.of(aUser, matcher)).get();
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
