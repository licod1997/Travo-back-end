package com.travo.service;

import com.travo.constant.RoleConstant;
import com.travo.dto.SignupDTO;
import com.travo.model.Role;
import com.travo.model.User;
import com.travo.repository.RoleRepository;
import com.travo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SignupServiceImpl implements SignupService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SignupServiceImpl(UserRepository userRepository,
                             RoleRepository roleRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public User findUser(SignupDTO signupDTO) {
        return userRepository.findByUsername(signupDTO.getUsername());
    }

    @Override
    public User saveUser(SignupDTO signupDTO) {
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(signupDTO.getPassword()));
        user.setFullName(signupDTO.getFullName());
        user.setEnable(true);

        Role role = roleRepository.findByName(RoleConstant.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);

        return userRepository.save(user);
    }
}
