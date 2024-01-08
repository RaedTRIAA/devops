package com.poly.gestioncatalogue5gr1.security.service;

import com.poly.gestioncatalogue5gr1.security.entities.AppRole;
import com.poly.gestioncatalogue5gr1.security.entities.AppUser;
import com.poly.gestioncatalogue5gr1.security.repository.RoleRepository;
import com.poly.gestioncatalogue5gr1.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountService implements IAccountService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void addRole(String role) {
       // roleRepository.save(new AppRole(role));
        roleRepository.save(AppRole.builder().role(role).build());
    }

    @Override
    public void addUser(String username, String password, String mail) {
        AppUser user=userRepository.findAppUserByUsername(username);
        if(user!=null) throw new RuntimeException("user existe");
        userRepository.save(AppUser
                .builder()
                        .id(UUID.randomUUID().toString())
                        .mail(mail)
                        .username(username)
                        .password(passwordEncoder.encode(password))
                .build());




    }

    @Override
    public void addroleToUser(String usename, String role) {

        AppUser user=loadUserByUserName(usename);
        AppRole rol=roleRepository.findById(role).orElse(null);
        user.getRoles().add(rol);

    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return userRepository.findAppUserByUsername(username);
    }
}
