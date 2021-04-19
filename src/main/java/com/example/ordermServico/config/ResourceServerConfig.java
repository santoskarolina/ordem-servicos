package com.example.ordermServico.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().antMatchers("/clientes").hasRole("USER");	
		http.authorizeRequests().antMatchers("/usuarios").permitAll();	
		http.authorizeRequests().antMatchers("/clientes/**").authenticated();
		http.authorizeRequests().antMatchers("/servicos/**").authenticated();
		http.authorizeRequests().anyRequest().denyAll();
	}
}
