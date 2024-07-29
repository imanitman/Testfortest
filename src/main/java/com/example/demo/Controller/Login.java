package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.Account;

import org.hibernate.sql.exec.ExecutionException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = RequestMethod.POST)
public class Login {   

    @PostMapping("/auth/login")
    public ResponseEntity<Account> loginPage(@RequestBody Account acc) {
        Account acc_origin = new Account();
        acc.setId(1);
        acc_origin.setPassword("nam102");
        acc_origin.setUsername("nam@gmail.com");
        if (acc.getUsername() != "nam@gmail.com" || acc.getPassword() != "nam102"){
            throw new ExecutionException("Ban nhap sai mk or tk");
        }
        return ResponseEntity.ok().body(acc_origin);
    }   
}
