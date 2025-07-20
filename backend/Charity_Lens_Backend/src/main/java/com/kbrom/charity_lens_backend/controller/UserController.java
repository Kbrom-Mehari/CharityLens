package com.kbrom.charity_lens_backend.controller;

import com.kbrom.charity_lens_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kbrom.charity_lens_backend.dto.GetUserDTO;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<GetUserDTO> getUserByEmail(@RequestParam String email) {
        GetUserDTO user= userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<GetUserDTO>> getAllUsers() {
        List<GetUserDTO> users= userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
