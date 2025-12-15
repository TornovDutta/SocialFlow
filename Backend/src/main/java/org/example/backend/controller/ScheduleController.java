package org.example.backend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.PostResponse;
import org.example.backend.DTO.ScheduleTask;
import org.example.backend.config.CustomUserDetails;
import org.example.backend.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;
    @GetMapping("/show")
    public ResponseEntity<List<ScheduleTask>> getAll(@AuthenticationPrincipal CustomUserDetails details){
        String id=details.getId();
        return new ResponseEntity<>(service.getAll(id), HttpStatus.OK);
    }
}
