package com.aryles.service;

import com.aryles.entities.AppRole;
import com.aryles.entities.AppUser;

public interface AccountService {

    public AppUser saveUser(String username, String password, String confirmedPassword);
    public AppRole save(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username, String roleName);
}
