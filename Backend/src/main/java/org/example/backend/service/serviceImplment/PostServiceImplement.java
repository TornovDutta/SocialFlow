package org.example.backend.service.serviceImplment;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.PostResponse;
import org.example.backend.model.Posts;
import org.example.backend.repo.PostRepo;
import org.example.backend.service.PostService;
import org.example.backend.utility.PostResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImplement  implements PostService {
    private final PostRepo repo;
    private final PostResponseMapper mapper;

    @Override
    public List<PostResponse> getAllPost(String userId) {
        List<Posts> posts = repo.findByUserId(userId);
        return mapper.toDTO(posts);
    }
}
