package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.UserRequested;
import org.example.backend.config.CustomUserDetails;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UserService service;


    @PutMapping("/me")
    public ResponseEntity<?> updateUsers(@RequestBody UserRequested requested,
                                         @AuthenticationPrincipal CustomUserDetails details) throws UserNotFoundException{
        String id=details.getId();
        return new ResponseEntity<>(service.upadte(id,requested),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUsers(@AuthenticationPrincipal CustomUserDetails details) throws UserNotFoundException{
        String id=details.getId();
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
