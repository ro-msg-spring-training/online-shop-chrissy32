package ro.msg.learning.shop.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import ro.msg.learning.shop.configuration.SecurityTypeConfiguration;

@Order(1)
public class FormLoginConfiguration extends SecurityTypeConfiguration {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/products/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}