// UserController.java
package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.User;
import com.example.demo.Domain.UserLogin;
import com.example.demo.Service.UserService;
  @RestController
  @RequestMapping("/auth") // Sử dụng namespace /api/auth
  public class LoginController {
  
    @Autowired
    private UserService userService; // Dịch vụ xử lý nghiệp vụ người dùng
  
    @Autowired
    private PasswordEncoder passwordEncoder; // Dịch vụ mã hóa password
  
    @PostMapping("/login")
    public ResponseEntity<User> authenticateUser(@RequestBody UserLogin loginRequest) {
      // Tìm kiếm người dùng dựa trên email
      User user = userService.getUserByEmail(loginRequest.getEmail());
  
      if (user == null) {
        throw new UsernameNotFoundException("User not found");
      }
  
      // So sánh mật khẩu đã mã hóa
      if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        throw new BadCredentialsException("Invalid password");
      }
  
      // Xác thực thành công, trả về thông tin người dùng (có thể ẩn bớt password)
      user.setPassword(null); // Xóa password trước khi trả về
      return ResponseEntity.ok(user);
    }
  }
  