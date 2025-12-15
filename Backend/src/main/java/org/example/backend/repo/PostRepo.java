package org.example.backend.repo;

import org.example.backend.model.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends MongoRepository<Posts, String> {
    List<Posts> findByUserId(String userId);
    List<Posts> findByUserIdAndPosted(String userId, boolean posted);
}
