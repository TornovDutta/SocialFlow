package org.example.backend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.PostResponse;
import org.example.backend.config.CustomUserDetails;
import org.example.backend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class PostController {
    private final PostService service;

    @GetMapping("/getPosts")
    public ResponseEntity<List<PostResponse>> getAll(@AuthenticationPrincipal CustomUserDetails details){
        String id=details.getId();
        return  new ResponseEntity<>(service.getAllPost(id), HttpStatus.OK);
    }

}
