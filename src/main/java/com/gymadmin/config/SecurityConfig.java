package com.gymadmin.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private DataSource dataSource;
 
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
 
	  auth.jdbcAuthentication().dataSource(dataSource)
	  	.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery(
			"select nick, password, enabled from users where nick=?")
		.authoritiesByUsernameQuery(
			"select user_id , role from user_roles where user_id=?");
	}	
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
	  http.authorizeRequests()
		.antMatchers("/**").access("hasRole('ROLE_ADMIN')")
		.and()
		  .formLogin().loginPage("/login").failureUrl("/login?error")
		  .usernameParameter("username").passwordParameter("password")
		.and()
		  .logout().logoutSuccessUrl("/login?logout")
		.and()
		  .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}