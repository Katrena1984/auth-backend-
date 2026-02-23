package org.example.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.auth.Dto.UserDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.auth.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/by-email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id)
            throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}