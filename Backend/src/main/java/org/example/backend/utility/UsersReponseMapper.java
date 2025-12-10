package org.example.backend.utility;

import org.example.backend.DTO.UsersReponse;
import org.example.backend.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersReponseMapper {
    public UsersReponse toDTO(Users users){
        UsersReponse user=new UsersReponse(users.getId(), users.getUsername() );
        return user;

    }
    public List<UsersReponse> toDTO(List<Users> users){
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public Users toEntity(UsersReponse users){
        Users users1=new Users();
        users1.setId(users.id());
        users1.setUsername(users.name());
        return users1;
    }
    public List<Users> toEntity(List<UsersReponse> users){
        return users.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
