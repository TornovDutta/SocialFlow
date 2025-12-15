package org.example.backend.service.serviceImplment;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.PostResponse;
import org.example.backend.DTO.ScheduleTask;
import org.example.backend.model.Posts;
import org.example.backend.repo.PostRepo;
import org.example.backend.service.ScheduleService;
import org.example.backend.utility.ScheduleTaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {
    private final PostRepo repo;
    private final ScheduleTaskMapper mapper;

    @Override
    public List<ScheduleTask> getAll(String id) {
        List<Posts> posts=repo.findByUserIdAndPosted(id,false);
        return mapper.toDTO(posts);
    }
}
