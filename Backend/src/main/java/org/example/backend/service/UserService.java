package org.example.backend.service;

import org.example.backend.DTO.UserRequested;
import org.example.backend.DTO.UsersReponse;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<UsersReponse> getAll();

    UsersReponse get(String id) throws UserNotFoundException;

    UsersReponse upadte(String id, UserRequested requested) throws UserNotFoundException;

    void delete(String id) throws UserNotFoundException;
}
