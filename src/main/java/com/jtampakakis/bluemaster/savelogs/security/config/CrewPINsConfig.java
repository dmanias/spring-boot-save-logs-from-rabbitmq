package com.jtampakakis.bluemaster.savelogs.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled= true)  
public class CrewPINsConfig extends ResourceServerConfigurerAdapter{

	private static final String RESOURCE_ID = "orchestrator";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		System.out.println(http.toString());
		http.
                anonymous().disable()
                .authorizeRequests() 
                .antMatchers(HttpMethod.GET,"/orders/**").hasAuthority("READ_PINORDER")
                .antMatchers(HttpMethod.POST,"/orders").hasAuthority("CREATE_PINORDER")
                .antMatchers(HttpMethod.PUT,"/orders/**").hasAuthority("UPDATE_PINORDER")
                
                .antMatchers(HttpMethod.GET,"/profiles/**").hasAuthority("READ_PINPROFILE")
                .antMatchers(HttpMethod.POST,"/profiles").hasAuthority("CREATE_PINPROFILE")
                .antMatchers(HttpMethod.DELETE,"/profiles/**").hasAuthority("DELETE_PINPROFILE")
                .antMatchers(HttpMethod.PUT,"/profiles/**").hasAuthority("UPDATE_PINPROFILE") 
         
                .anyRequest()
                .authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}
