package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.UserRequested;
import org.example.backend.DTO.UsersReponse;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.Users;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UserService service;
    @GetMapping("/")
    public ResponseEntity<List<UsersReponse>> getUsers() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsesById(@PathVariable String id) throws UserNotFoundException {
        return new ResponseEntity<>(service.get(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsers(@PathVariable String id,@RequestBody UserRequested requested) throws UserNotFoundException{
        return new ResponseEntity<>(service.upadte(id,requested),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable String id) throws UserNotFoundException{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
