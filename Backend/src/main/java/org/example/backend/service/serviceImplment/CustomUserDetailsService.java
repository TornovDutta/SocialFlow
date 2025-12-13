package org.example.backend.service.serviceImplment;

import lombok.RequiredArgsConstructor;
import org.example.backend.config.CustomUserDetails;
import org.example.backend.model.Users;
import org.example.backend.repo.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users user = userRepository
                .findByUsername(username).orElseThrow(()->
                        new UsernameNotFoundException("user not found"));

        return new CustomUserDetails(user);
    }
}
