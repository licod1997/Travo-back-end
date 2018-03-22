package com.travo.service;

import com.travo.dto.SignupDTO;
import com.travo.model.User;

public interface SignupService {
    User findUser(SignupDTO signupDTO);
    User saveUser(SignupDTO signupDTO);
}
