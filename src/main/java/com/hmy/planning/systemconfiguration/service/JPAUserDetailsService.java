package com.hmy.planning.systemconfiguration.service;

import java.util.Optional;

import com.hmy.planning.systemconfiguration.models.users;
import com.hmy.planning.systemconfiguration.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<users> user = userRepository.findByUsername(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " +
                userName));
        return user.map(MyUserDetails::new).get();

    }

    /*
     * public UserDetails findUserById(Integer id) throws
     * UsernameNotFoundException
     * {
     * Optional<User> user = userRepository.findById(id);
     * user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + id));
     * return user.map(MyUserDetails::new).get();
     * }
     *
     * public UserDetails getLoggedInUser() {
     * UserDetails userDetails = (UserDetails)
     * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     * return userDetails;
     * }
     */

}
