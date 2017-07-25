package com.converter.poc.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.converter.poc.dao.UserDAO;
import com.converter.poc.entity.User;
import com.converter.poc.entity.UserRoles;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService{
   
	
	
	@Autowired
    private UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);	
    private static final String LOGGER_INFO = "loggerInfo:";
    
    //authorise and authenticate the user while logging in
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRoles role : user.getRole()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
         }
        logger.info(String.format(LOGGER_INFO+"loadUserByUsername: UserDetailsServiceImpl class"));
         return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
