package com.kharthik.bankingsystem.controller;
import com.kharthik.bankingsystem.dto.LoginDTO;
import com.kharthik.bankingsystem.dto.RegisterDTO;
import com.kharthik.bankingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO registerDTO) {
        System.out.println("Controller Hit");
        return userService.register(registerDTO);


    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }
}
