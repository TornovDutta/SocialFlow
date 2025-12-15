package org.example.backend.service;

import org.example.backend.DTO.ScheduleTask;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    List<ScheduleTask> getAll(String id);

    void scheduleTask(LocalDateTime time);
}
