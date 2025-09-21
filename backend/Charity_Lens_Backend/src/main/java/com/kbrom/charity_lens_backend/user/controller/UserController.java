package com.kbrom.charity_lens_backend.user.controller;

import com.kbrom.charity_lens_backend.common.dto.ApiResponse;
import com.kbrom.charity_lens_backend.user.dto.UpdateDonorProfileDTO;
import com.kbrom.charity_lens_backend.user.model.User;
import com.kbrom.charity_lens_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kbrom.charity_lens_backend.user.dto.GetDonorProfileDTO;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PreAuthorize("hasRole('SYS_ADMIN')")
    @GetMapping
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user= userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user= userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users= userService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('DONOR') and #id==authentication.principal.id")
    public ResponseEntity<GetDonorProfileDTO> updateDonorProfile(@PathVariable Long id, UpdateDonorProfileDTO updateUserDTO) {
        GetDonorProfileDTO updated= userService.updateDonorProfile(updateUserDTO,id);
        return ResponseEntity.ok(updated);
    }
    public ResponseEntity<ApiResponse> disableUser(@PathVariable Long id) {
        userService.disableUserById(id);
        return ResponseEntity.ok().body(new ApiResponse("User disabled !",true));
    }
}
