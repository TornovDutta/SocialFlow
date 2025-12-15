package org.example.backend.DTO;

import org.example.backend.model.Platform;

import java.time.LocalDateTime;

public record PostResponse(String id, String content, LocalDateTime dateTime, boolean status, Platform platform) {
}
