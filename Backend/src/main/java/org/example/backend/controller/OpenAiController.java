package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.OpenAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenAiController {
    private final OpenAiService service;
    @GetMapping("/")
    public String genareted(@RequestParam String content){
        return service.generate(content);
    }
}
