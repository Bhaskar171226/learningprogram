package com.inventarymanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure( AuthenticationManagerBuilder auth){
		try {
			auth.inMemoryAuthentication().withUser("vallapu").password("bhaskar").roles("USER").and().withUser("mohan").password("vs").roles("ADMIN");
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) {
		
		try {
			httpSecurity.authorizeRequests().anyRequest().hasAnyRole("USER").and().httpBasic();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			httpSecurity.csrf().disable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
