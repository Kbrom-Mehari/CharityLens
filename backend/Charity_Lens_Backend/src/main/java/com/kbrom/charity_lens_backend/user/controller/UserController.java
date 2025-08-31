package com.kbrom.charity_lens_backend.user.controller;

import com.kbrom.charity_lens_backend.user.dto.RegisterUserDTO;
import com.kbrom.charity_lens_backend.user.dto.UserUpdateDTO;
import com.kbrom.charity_lens_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kbrom.charity_lens_backend.user.dto.GetUserDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<GetUserDTO> getUserByEmail(@RequestParam String email) {
        GetUserDTO user= userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetUserDTO> getUserById(@PathVariable Long id) {
        GetUserDTO user= userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<GetUserDTO>> getAllUsers() {
        List<GetUserDTO> users= userService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<GetUserDTO> registerUser(RegisterUserDTO registerUserDTO) {
        GetUserDTO createdUser=userService.createUser(registerUserDTO);
        URI location=URI.create("/user/"+createdUser.getId());
        return ResponseEntity.created(location).body(createdUser);

    }
    @PatchMapping("/{id}")
    public ResponseEntity<GetUserDTO> updateUser(@PathVariable Long id, UserUpdateDTO updateUserDTO) {
        GetUserDTO updated= userService.updateUser(updateUserDTO,id);
        return ResponseEntity.ok(updated);
    }
}
