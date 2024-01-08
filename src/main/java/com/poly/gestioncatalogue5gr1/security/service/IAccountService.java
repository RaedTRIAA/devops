package com.poly.gestioncatalogue5gr1.security.service;

import com.poly.gestioncatalogue5gr1.security.entities.AppRole;
import com.poly.gestioncatalogue5gr1.security.entities.AppUser;

public interface IAccountService {

    public void addRole(String role);
    public void addUser(String username,String password,String mail);
    public void addroleToUser(String usename,String role);
    public AppUser loadUserByUserName(String username);
}
