package org.example.backend.DTO;

import java.time.LocalDateTime;

public record ScheduleTask(LocalDateTime dateTime, String name) {
}
