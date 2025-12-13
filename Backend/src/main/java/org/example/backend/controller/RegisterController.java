package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.UserRequested;
import org.example.backend.DTO.UsersReponse;
import org.example.backend.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class RegisterController {
    private final RegisterService service;
    @PostMapping("")
    public ResponseEntity<UsersReponse> registe(@RequestBody UserRequested requested){
        return new ResponseEntity<>(service.add(requested), HttpStatus.CREATED);
    }
}
