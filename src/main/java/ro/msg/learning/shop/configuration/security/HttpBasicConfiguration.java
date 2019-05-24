package ro.msg.learning.shop.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
//@ConditionalOnProperty(name = "security.type", havingValue = "basic")
@EnableWebSecurity
@Profile("basic")
@Order(101)
public class HttpBasicConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private CustomAuthenticationProvider authProvider;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/oauth/token", "/login").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .httpBasic()
            .authenticationEntryPoint(authenticationEntryPoint);
    }

}
