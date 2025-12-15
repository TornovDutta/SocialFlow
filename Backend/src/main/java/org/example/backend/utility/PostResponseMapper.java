package org.example.backend.utility;

import org.example.backend.DTO.PostResponse;
import org.example.backend.model.Posts;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostResponseMapper {
    public PostResponse toDTO(Posts posts){
        PostResponse response=new PostResponse(posts.getId(), posts.getContent(), posts.getCreatedAt(),
                posts.isPosted(), posts.getPlatform());
        return response;
    }
    public List<PostResponse> toDTO(List<Posts> posts){
        return posts.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
