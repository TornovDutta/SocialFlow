package org.example.backend.service;

import org.example.backend.DTO.PostResponse;

import java.util.List;

public interface PostService  {
    List<PostResponse> getAllPost(String id);

    String  post(String content,String id);
}
