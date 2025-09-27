package com.kbrom.charity_lens_backend.user;

import com.kbrom.charity_lens_backend.auth.dto.ChangePasswordDTO;
import com.kbrom.charity_lens_backend.common.dto.ApiResponse;
import com.kbrom.charity_lens_backend.user.dto.UpdateDonorProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kbrom.charity_lens_backend.user.dto.GetDonorProfileDTO;

import java.security.Principal;
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
    @GetMapping("/{id}/flag")
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<ApiResponse> flagUser(@PathVariable Long id) {
        userService.flagUserById(id);
        return ResponseEntity.ok().body(new ApiResponse("User flagged !",true));
    }
    @GetMapping("/{id}/disable-account")
    @PreAuthorize("hasRole('SYS_ADMIN')or #id==authenticated.principal.id")
    public ResponseEntity<ApiResponse> disableUser(@PathVariable Long id) {
        userService.disableUserById(id);
        return ResponseEntity.ok().body(new ApiResponse("User disabled !",true));
    }
    @PutMapping("/{id}/update-password")
    @PreAuthorize("#id==authenticated.principal.id")
    public ResponseEntity<ApiResponse> changePassword (@PathVariable Long id, @RequestBody ChangePasswordDTO changePasswordDTO, Principal principal) {
        userService.changePassword(id,changePasswordDTO,principal);
        return ResponseEntity.ok().body(new ApiResponse("Password changed !",true));
    }
}
