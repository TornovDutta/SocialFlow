package org.example.backend.service.serviceImplment;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.UserRequested;
import org.example.backend.DTO.UsersReponse;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.model.Users;
import org.example.backend.repo.UsersRepo;
import org.example.backend.service.UserService;
import org.example.backend.utility.UsersReponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UsersRepo repo;
    private final UsersReponseMapper mapper;

    @Override
    public List<UsersReponse> getAll() {
        List<Users> allUser=repo.findAll();
        return mapper.toDTO(allUser);
    }

    @Override
    public UsersReponse get(String id) throws UserNotFoundException {
        Users users=repo.findById(id).orElseThrow(()->
                new UserNotFoundException("wrong id"));
        return mapper.toDTO(users);
    }

    @Override
    public UsersReponse upadte(String id, UserRequested requested) throws UserNotFoundException {
        Users users=repo.findById(id).orElseThrow(()->
                new UserNotFoundException("wrong id"));
        users.setUsername(requested.name());
        users.setPassword(requested.password());
        repo.save(users);
        return mapper.toDTO(users);
    }

    @Override
    public void delete(String id) throws UserNotFoundException {
        Users users=repo.findById(id).orElseThrow(()->
                new UserNotFoundException("wrong id"));
        repo.removeById(id);
    }
}
