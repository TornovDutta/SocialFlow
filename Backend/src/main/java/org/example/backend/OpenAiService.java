package org.example.backend;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

@Service
public class OpenAiService {
    @Value("${openai.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public OpenAiService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/responses")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @PostConstruct
    public void checkKey() {
        System.out.println("Loaded OpenAI Key: " + (apiKey == null ? "NULL" : "OK"));
    }

    public String generatePost(String prompt) {

        Map<String, Object> body = Map.of(
                "model", "gpt-4.1-mini",
                "input", prompt
        );

        return webClient.post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(2))
                                .filter(ex -> ex instanceof org.springframework.web.reactive.function.client.WebClientResponseException.TooManyRequests)
                )
                .map(response -> {
                    try {

                        var output = (java.util.List<Map<String, Object>>) response.get("output");
                        var contentList = (java.util.List<Map<String, Object>>) output.get(0).get("content");
                        return contentList.get(0).get("text").toString();
                    } catch (Exception e) {
                        return "ERROR: Unable to parse OpenAI response.";
                    }
                })
                .block();
    }


    public String generate(String userText) {

        String prompt = """
    Create a professional, engaging LinkedIn post based on the following content.
    Add relevant emojis, keep it concise, and end with 2–3 relevant hashtags.

    Content:
    %s
    """.formatted(userText);

        return generatePost(prompt);
    }

}
