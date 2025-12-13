package org.example.backend.service.serviceImplment;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.UserRequested;
import org.example.backend.DTO.UsersReponse;
import org.example.backend.config.JwtUtil;
import org.example.backend.model.Users;
import org.example.backend.repo.UsersRepo;
import org.example.backend.service.RegisterService;
import org.example.backend.utility.UsersReponseMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImplement implements RegisterService {
    private final UsersRepo repo;
    private final UsersReponseMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UsersReponse add(UserRequested request) {
        Users user = new Users();
        user.setUsername(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("USER");


        Users savedUser = repo.save(user);


        String token = jwtUtil.generateToken(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole()
        );


        System.out.println("JWT TOKEN (DEV ONLY): " + token);

        return mapper.toDTO(savedUser);
    }
}
