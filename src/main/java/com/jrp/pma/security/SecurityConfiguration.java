package com.jrp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder; //this will encode passwords for registrations
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { //specifies who is logged in (who the user is)
		auth.jdbcAuthentication() //dataSource is taken from the autowired above
			.usersByUsernameQuery("select username, password, enabled "+ //this block of code alows for a custom schema structure in the authentication database storage. this is the authentication portion with 3 columns(select username, password, enabled).
					"from user_accounts where username = ?" ) //from the user_accounts table
			.authoritiesByUsernameQuery("select username, role "+ //this is the authorization portion with 2 colmns(select username, role)
					"from user_accounts where username = ?") //from the user_accounts table
			.dataSource(dataSource)
			.passwordEncoder(bCryptEncoder); //this will decode using bCrypt for retrieval 

		//this code block uses default hardcoded user and password schemas. schemas as in the structure of the database
//			.withDefaultSchema()
//			.withUser("myuser")
//				.password("pass")
//				.roles("USER")
//			.and()
//			.withUser("taz")
//				.password("pass2")
//				.roles("USER")
//			.and()
//			.withUser("managerUser")
//				.password("pass3")
//				.roles("ADMIN");
		

	}
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() { //this encodes the password to avoid it from being plain text which is a security concern
//		return NoOpPasswordEncoder.getInstance(); //this is to bypass having to encode passwords for the purpose of demonstrating
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { //specifies what the logged in user is allowed to do (type of user) through "roles"
		http.authorizeRequests() //WARNING!!! in the following lines, the priotrity depends on the order of the methods (the first being the top priority). so admin has to be first priority otherwise the low priority will override the top priorities.
//			.antMatchers("/projects/new").hasRole("ADMIN") //this is interpreted as: only admins can create new projects. antMachers() is where the endpoint/location are being configured for authorization.
//			.antMatchers("/projects/save").hasRole("ADMIN")// hasRole means you must prepend "ROLE_" in front of ADMIN in the database
//			.antMatchers("/projects/new").hasAuthority("ADMIN")
//			.antMatchers("/projects/save").hasAuthority("ADMIN")
//			.antMatchers("/employees/new").hasAuthority("ADMIN") //this is interpreted as: only admins can create new projects. antMachers() is where the endpoint/location are being configured for authorization.
//			.antMatchers("/employees/save").hasAuthority("ADMIN")// hasAuthority means you can just add ADMIN in the database
//			.antMatchers("/h2_console/**").permitAll()
//			.antMatchers("/").authenticated().and().formLogin() //anybody who has been authenticated can be able to access the root/home page. formLogin() gives the default login page.antMachers() is where the endpoint/location are being configured for authorization.
			.antMatchers("/", "/**").permitAll() // "/**" means that every route after the / is included. 
			.and()
			.formLogin();
			 //disable to be able to test HTTP requests when creating RESTful Routes
//			.loginPage("/login-page"); //allows for custom login page
		

//		http.headers().frameOptions().disable();
		http.csrf().disable(); 
		// in html, <form th:action="@{/employees/save}"></form> has csrf embedded/built-in when using thymeleaf. It can only be disabled here using http.csrf().disable(); like in the previous line. 
		// if connecting without thymeleaf, <form action="/employees/save"></form> you will have to add csrf with <input type="hidden" name="_csrf" th:value="${_csrf.token}"/>. To disable, you will have to comment it out or delete it from specific page or all 
		// pages depending on where you want to test. note that the csrf input should be placed before the button tag or <a> tag
		
	}
	
}
