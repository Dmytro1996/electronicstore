package com.mycompany.electronicstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.electronicstore.service.AiChatService;
import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
@Service
public class AiChatServiceImpl implements AiChatService{
    
    private final ChatClient chatClient;
    
    @Autowired
    public AiChatServiceImpl(ChatClient.Builder chatClientBuilder, 
            @Qualifier("availableLaptops")ToolCallback availableLaptops, 
            @Qualifier("availableMobileDevices")ToolCallback availableMobileDevices, 
            @Qualifier("availableTelevisions")ToolCallback availableTelevisions, 
            @Qualifier("availableAccesories")ToolCallback availableAccesories) {
        this.chatClient = chatClientBuilder.defaultSystem(
                "Youre a helpful AI assitant in an online electronic store " + 
                        "helping customers with their inquiries and providing " + 
                        "advice on buying electronic devices."
        ).defaultTools(List.of(availableLaptops, availableMobileDevices, 
                availableTelevisions, availableAccesories)).build();
    }

    @Override
    public String processMessage(String message) {
        return chatClient.prompt(message).call().content();
    }
    
}
