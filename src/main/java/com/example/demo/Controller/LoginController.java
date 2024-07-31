// UserController.java
package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.User;
import com.example.demo.Domain.UserLogin;
import com.example.demo.Domain.response.ResLoginDTO;
import com.example.demo.Service.UserService;
import com.example.demo.util.SecurityUtil;
  @RestController
  @RequestMapping("/auth") // Sử dụng namespace /api/auth
  public class LoginController {
  
    @Autowired
    private UserService userService; // Dịch vụ xử lý nghiệp vụ người dùng
  
    @Autowired
    private PasswordEncoder passwordEncoder; // Dịch vụ mã hóa password
    
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private SecurityUtil securityUtil;
    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> authenticateUser(@RequestBody UserLogin loginRequest) {
      // Tìm kiếm người dùng dựa trên email
      User user = userService.getUserByEmail(loginRequest.getEmail());
      //
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
      // AuthenticationManager sử dụng thông tin của authenticationToken để tìm người dùng trong cơ sở dữ liệu
      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      //set thông tin người dùng vào context sử dụng sau này
      SecurityContextHolder.getContext().setAuthentication(authentication);
  
      String accessToken = securityUtil.createAccessToken(user);
      ResLoginDTO resLogin = new ResLoginDTO();
      resLogin.setAccessToken(accessToken);
      ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin();
      userLogin.setEmail(user.getEmail());
      userLogin.setName(user.getName());
      userLogin.setId(user.getId());
      resLogin.setUserLogin(userLogin);
      return ResponseEntity.ok().body(resLogin);
    }
  }
  