package org.example.backend.service.serviceImplment;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.PostResponse;
import org.example.backend.DTO.ScheduleTask;
import org.example.backend.model.Posts;
import org.example.backend.repo.PostRepo;
import org.example.backend.service.ScheduleService;
import org.example.backend.utility.ScheduleTaskMapper;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {
    private final PostRepo repo;
    private final ScheduleTaskMapper mapper;
    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledTask;

    @Override
    public List<ScheduleTask> getAll(String id) {
        List<Posts> posts=repo.findByUserIdAndPosted(id,false);
        return mapper.toDTO(posts);
    }

    @Override
    public void scheduleTask(LocalDateTime time) {
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
        }

        scheduledTask = taskScheduler.schedule(
                this::executeTask,
                Date.from(time.atZone(ZoneId.systemDefault()).toInstant())
        );
    }
    private void executeTask() {
        System.out.println("Task executed at: " + LocalDateTime.now());
    }
}
