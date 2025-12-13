package org.example.backend.service;

import org.example.backend.DTO.UserRequested;
import org.example.backend.DTO.UsersReponse;

public interface RegisterService {
    UsersReponse add(UserRequested requested);
}
