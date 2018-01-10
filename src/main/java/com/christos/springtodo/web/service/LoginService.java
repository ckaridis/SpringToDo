package com.christos.springtodo.web.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean validateUser (String userid, String password){
        return userid.equalsIgnoreCase("test@test.com") && password.equalsIgnoreCase("123456");
    }
}
