package org.example.backend.utility;

import org.example.backend.DTO.ScheduleTask;
import org.example.backend.DTO.UsersReponse;
import org.example.backend.model.Posts;
import org.example.backend.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleTaskMapper {
    public ScheduleTask toDTO(Posts post){

        String content = post.getContent();
        String name = buildPreviewName(content);

        return new ScheduleTask(post.getCreatedAt(), name);
    }
    public List<ScheduleTask> toDTO(List<Posts> posts){
        return posts.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    private String buildPreviewName(String content) {
        if (content == null || content.isBlank()) {
            return "";
        }

        String[] words = content.trim().split("\\s+");

        if (words.length <= 3) {
            return content;
        }

        return words[0] + " " + words[1] + " " + words[2] + " ...";
    }
}
