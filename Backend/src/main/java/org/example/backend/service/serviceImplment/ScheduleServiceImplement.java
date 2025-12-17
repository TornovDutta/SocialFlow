package org.example.backend.service.serviceImplment;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.ScheduleTask;
import org.example.backend.model.Platform;
import org.example.backend.model.Posts;
import org.example.backend.model.Users;
import org.example.backend.repo.PostRepo;
import org.example.backend.repo.UsersRepo;
import org.example.backend.service.ScheduleService;
import org.example.backend.utility.ScheduleTaskMapper;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplement implements ScheduleService {
    private final PostRepo repo;
    private final ScheduleTaskMapper mapper;
    private final TaskScheduler taskScheduler;
    private final UsersRepo usersRepo;
    private final MailServiceImplement mailService;

    private final List<ScheduledFuture<?>> scheduledTasks = new CopyOnWriteArrayList<>();

    @Override
    public List<ScheduleTask> getAll(String id) {
        List<Posts> posts = repo.findByUserIdAndPosted(id, false);
        return mapper.toDTO(posts);
    }

    @Override
    public void scheduleTask(String id, LocalDateTime time, String content) {

        Users user = usersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String email = user.getEmail();




        mailService.mail(
                "Your post is scheduled: " + content,
                email
        );
        Posts posts=new Posts();
        posts.setContent(content);
        posts.setPosted(false);
        posts.setPlatform(Platform.LINKEDIN);
        posts.setUserId(id);
        Posts savePost=repo.save(posts);

        ScheduledFuture<?> task = taskScheduler.schedule(
                () -> {
                    try {
                        executeTask(savePost.getId(),email, content);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                Date.from(time.atZone(ZoneId.systemDefault()).toInstant())
        );

        scheduledTasks.add(task);
    }

    private void executeTask(String id,String email, String content) throws Exception {

        mailService.mail(
                "Your post has been published: " ,
                email
        );
        Posts post=repo.findById(id).orElseThrow(()->
                new Exception("Post not found"));

        post.setPosted(true);

        repo.save(post);


    }

}
