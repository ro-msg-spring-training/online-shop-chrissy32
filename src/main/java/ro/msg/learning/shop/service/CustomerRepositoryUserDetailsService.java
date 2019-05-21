package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.ICustomerRepository;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomerRepositoryUserDetailsService implements UserDetailsService {
    private ICustomerRepository customerRepository;

    @Override
    public User loadUserByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);

        if (customer == null)
            throw new UsernameNotFoundException("User '" + username + "' not found!");

        return new User(customer.getUsername(), customer.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
