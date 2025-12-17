package org.example.backend.controller;


import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.PostRequested;
import org.example.backend.DTO.PostResponse;
import org.example.backend.DTO.ScheduleTask;
import org.example.backend.config.CustomUserDetails;
import org.example.backend.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;
    @GetMapping("/show")
    public ResponseEntity<List<ScheduleTask>> getAll(@AuthenticationPrincipal CustomUserDetails details){
        String id=details.getId();
        return new ResponseEntity<>(service.getAll(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> schedule(
            @RequestBody PostRequested requested,
            @AuthenticationPrincipal CustomUserDetails details) {

        LocalDateTime time = LocalDateTime.parse(requested.time());
        service.scheduleTask(details.getId(), time, requested.content());
//        System.out.println(time.getHour()+""+details.getId()+""+requested.content());
        return ResponseEntity.ok().build();
    }
}
