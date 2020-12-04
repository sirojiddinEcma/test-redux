package uz.pdp.appwarehouseg8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


    /**
     * parolni tekshiramiz, ammo token qaytarmaymiz
     * @param loginDto
     * @return
     */
    @PostMapping("checkPasswordAndLogin")
    public HttpEntity<?> checkPasswordAndLogin(@Valid @RequestBody LoginDto loginDto) {
        ApiResponse apiResponse = authService.checkPasswordAndLogin(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PostMapping("login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        ApiResponse apiResponse = authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @GetMapping("verification")
    public HttpEntity<?> verificationEmail(@RequestParam String email, @RequestParam String code) {
        ApiResponse apiResponse = authService.verificationEmail(email, code);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
