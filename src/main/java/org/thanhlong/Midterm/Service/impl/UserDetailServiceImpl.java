package org.thanhlong.Midterm.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.Models.User;
import org.thanhlong.Midterm.Implement.CustomUserDetails;
import org.thanhlong.Midterm.Repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user!!");
        }
        return new CustomUserDetails(user);
    }

}