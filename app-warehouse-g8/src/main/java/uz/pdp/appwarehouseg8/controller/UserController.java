package uz.pdp.appwarehouseg8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouseg8.entity.User;
import uz.pdp.appwarehouseg8.payload.ApiResponse;
import uz.pdp.appwarehouseg8.payload.UserDto;
import uz.pdp.appwarehouseg8.security.CurrentUser;
import uz.pdp.appwarehouseg8.service.UserService;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("me")
    public HttpEntity<?> getUserMe(@CurrentUser User user) {
        return ResponseEntity.ok(new ApiResponse("Mana sanga o'zing", true, user));
    }

    @PutMapping("/edit")
    public HttpEntity<?> editProfile(@RequestBody UserDto userDto, @CurrentUser User user) {
        ApiResponse response = userService.editProfile(userDto,user);
        return ResponseEntity.status(response.isSuccess() ? 202 : 409).body(response);
    }
}
