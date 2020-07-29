package ro.msg.learning.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.User;
import ro.msg.learning.shop.repository.CustomerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("-----loadUserByUsername -- method entered, username = {}", username);
        Customer customer = this.customerRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("userDetailsServiceImpl.getPasswordForUsername: Invalid username [%username]!"));
        User user = new User();
        user.setPassword(customer.getPassword());
        user.setUsername(customer.getUsername());
        log.info("-----loadUserByUsername -- method finished, user = {}", user);
        return user;
    }
}
