package com.kharthik.bankingsystem.service;
import com.kharthik.bankingsystem.dto.LoginDTO;
import com.kharthik.bankingsystem.entity.User;
import com.kharthik.bankingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kharthik.bankingsystem.dto.RegisterDTO;
import com.kharthik.bankingsystem.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String register(RegisterDTO registerDTO) {
        System.out.println("Service Hit");
        User existingUser = userRepository.findByUsername(registerDTO.getUsername());
        if (existingUser != null) {
            return "Username Already Exists";
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(registerDTO.getRole());

        System.out.println("Before Save");
        userRepository.save(user);

        System.out.println("After Save");
        return "User Registered Successfully";
    }
    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user == null) {
            return "User Not Found";
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return "Invalid Password";
        }
        String token = jwtService.generateToken(user.getUsername());
        return token;
    }









}
