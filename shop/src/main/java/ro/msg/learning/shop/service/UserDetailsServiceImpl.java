package ro.msg.learning.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.User;
import ro.msg.learning.shop.repository.CustomerRepository;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("-----loadUserByUsername -- method entered, username = {}", username);
        Customer customer = this.customerRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("userDetailsServiceImpl.getPasswordForUsername: Invalid username [%username]!"));
        User user = new User(customer, "ROLE_CUSTOMER");
        log.info("-----loadUserByUsername -- method finished, user = {}", user);
        return user;
    }
}
