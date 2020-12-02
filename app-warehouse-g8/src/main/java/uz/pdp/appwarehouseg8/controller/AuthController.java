package uz.pdp.appwarehouseg8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appwarehouseg8.payload.ApiResponse;
import uz.pdp.appwarehouseg8.payload.LoginDto;
import uz.pdp.appwarehouseg8.payload.RegisterDto;
import uz.pdp.appwarehouseg8.repository.UserRepository;
import uz.pdp.appwarehouseg8.service.AuthService;

import javax.validation.Valid;

/**
 * BY SIROJIDDIN on 28.11.2020
 */


@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;


    @PostMapping("register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = authService.register(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @PostMapping("login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        ApiResponse apiResponse = authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }
}
