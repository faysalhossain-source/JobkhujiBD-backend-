package com.faysal.Jobkhujibd_backend.controller;






import jakarta.validation.Valid;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/jobseeker")
    public UserDetails user(@CurrentUser UserDetails currentUser) {
        return currentUser;
    }
    @GetMapping("/employer")
    public UserDetails user(@CurrentUser UserDetails currentUser) {
        return currentUser;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id).map(user -> ResponseEntity.ok(convertToDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public List<UserResponse> getUsersByRole(@PathVariable Role role) {
        return userService.getUsersByRole(role)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        User user = new User(
                userCreateRequest.email(),
                userCreateRequest.password(),
                userCreateRequest.role(),
                userCreateRequest.mobile(),
                userCreateRequest.city(),
                userCreateRequest.state(),
                userCreateRequest.pincode(),
                userCreateRequest.companyName(),
                userCreateRequest.companyAddress()

        );
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(createdUser));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            User userDetails = new User();
            userDetails.setEmail(userUpdateRequest.email());
            userDetails.setPassword(userUpdateRequest.password());
            userDetails.setPhoneNumber(userUpdateRequest.mobile());
            userDetails.setCity(userUpdateRequest.city());
            userDetails.setState(userUpdateRequest.state());
            userDetails.setPinCode(userUpdateRequest.pincode());
            userDetails.setCompanyName(userUpdateRequest.companyName());
            userDetails.setCompanyAddress(userUpdateRequest.companyAddress());

            // Only allow role update if the user is an admin
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                userDetails.setRole(userUpdateRequest.role());
            }

            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(convertToDTO(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(convertToDTO(currentUser));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(Authentication authentication,
                                            @Valid @RequestBody PasswordChangeRequest request) {
        try {
            User currentUser = userService.getCurrentUser(authentication);
            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            userService.changePassword(currentUser.getId(), request.currentPassword(), request.newPassword());

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    private UserResponse convertToDTO(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setMobile(user.getMobile());
        dto.setCity(user.getCity());
        dto.setState(user.getState());
        dto.setPinCode(user.getPinCode());
        dto.setCompanyName(user.getCompanyName());
        dto.setCompanyAddress(user.getCompanyAddress());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

}
