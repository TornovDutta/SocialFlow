package org.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String id;
    private String username;
    private String password;
    private String role="USER";
    private List<Posts> posts;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
