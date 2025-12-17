package org.example.backend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.PostRequested;
import org.example.backend.DTO.PostResponse;
import org.example.backend.config.CustomUserDetails;
import org.example.backend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/posts")
public class PostController {
    private final PostService service;

    @GetMapping("/getPosts")
    public ResponseEntity<List<PostResponse>> getAll(@AuthenticationPrincipal CustomUserDetails details){
        String id=details.getId();
        return  new ResponseEntity<>(service.getAllPost(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> post(@RequestBody PostRequested requested,@AuthenticationPrincipal CustomUserDetails details){
        String id=details.getId();
        String content=requested.content();
        return new ResponseEntity<>(service.post(content,id),HttpStatus.OK);
    }

}
