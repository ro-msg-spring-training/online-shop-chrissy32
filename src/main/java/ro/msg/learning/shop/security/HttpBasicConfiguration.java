package ro.msg.learning.shop.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import ro.msg.learning.shop.configuration.SecurityTypeConfiguration;

@Order(1)
@AllArgsConstructor
@RequiredArgsConstructor
public class HttpBasicConfiguration extends SecurityTypeConfiguration {
    private MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

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
            .httpBasic()
            .authenticationEntryPoint(myBasicAuthenticationEntryPoint);
    }
}
