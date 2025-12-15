package org.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    @Id
    private String id;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean posted;
    private Platform platform;

    private String userId;
}
