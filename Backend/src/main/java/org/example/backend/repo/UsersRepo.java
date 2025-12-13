package org.example.backend.repo;

import org.example.backend.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends MongoRepository<Users,String> {
    List<Users> readById(String id);

    void removeById(String id);


    Optional<Users> findByUsername(String username);



}
