package com.travo.service;

import com.travo.dto.UserDTO;
import com.travo.model.User;
import com.travo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by asus on 3/24/2018.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO findUserDTOById(Long id) {
       User user = userRepository.findById(id);
       UserDTO dto = new UserDTO();
       dto.setId(user.getId());
       dto.setUsername(user.getUsername());
       dto.setFullName(user.getFullName());
       dto.setEmail(user.getEmail());
       dto.setImageUrl(user.getImageUrl());
//       dto.setCreatedSpotIdArr(user.getS);
        return dto;
    }
}
