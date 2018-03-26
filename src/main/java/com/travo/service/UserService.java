package com.travo.service;

import com.travo.dto.UserDTO;
import com.travo.model.User;

/**
 * Created by asus on 3/24/2018.
 */
public interface UserService {
    User findUserById(Long id);
    UserDTO findUserDTOById(Long id);
    User findByUsername(String username);

}
