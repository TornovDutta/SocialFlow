package org.example.backend.service.serviceImplment;


import org.example.backend.service.OpenAiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;

import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceImplement implements OpenAiService {
    private final ChatClient chatClient;

    public OpenAiServiceImplement(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    public String getResponse(String prompt){
        return chatClient.prompt().user(prompt).call().content().trim();
    }

    @Override
    public String generate(String userText) {

        String prompt = """
    Create a professional, engaging LinkedIn post based on the following content.
    Add relevant emojis, keep it concise, and end with 2–3 relevant hashtags.

    Content:
    %s
    """.formatted(userText);

        return getResponse(prompt);
    }

}
