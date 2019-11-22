package com.aryles.sec;

import com.aryles.entities.AppUser;
import com.aryles.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=accountService.loadUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException("invalid user");
        Collection<GrantedAuthority> authorities =  new ArrayList<>();

        appUser.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });

        return new User(appUser.getUsername(),appUser.getPassword(),authorities);
    }
}
