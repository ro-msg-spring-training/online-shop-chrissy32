package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.msg.learning.shop.exceptions.InvalidSecurityTypeException;
import ro.msg.learning.shop.repository.ICustomerRepository;
import ro.msg.learning.shop.security.FormLoginConfiguration;
import ro.msg.learning.shop.security.HttpBasicConfiguration;
import ro.msg.learning.shop.security.MyBasicAuthenticationEntryPoint;
import ro.msg.learning.shop.service.CustomerRepositoryUserDetailsService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@RequiredArgsConstructor
public class SecurityTypeConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    MyBasicAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    ICustomerRepository customerRepository;

    private enum Security {
        BASIC, FORM
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new CustomerRepositoryUserDetailsService(customerRepository);
    }

    @Bean
    protected DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @Bean
    protected PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityTypeConfiguration securityType(@Value("${security.type}") Security security) {
        switch (security) {
            case BASIC:
                return new HttpBasicConfiguration(authenticationEntryPoint);
            case FORM:
                return new FormLoginConfiguration();
            default:
                throw new InvalidSecurityTypeException();
        }
    }
}
