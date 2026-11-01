package com.mycompany.electronicstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.electronicstore.service.AiChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
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
            ChatMemory chatMemory,
            @Qualifier("availableLaptops")ToolCallback availableLaptops, 
            @Qualifier("availableMobileDevices")ToolCallback availableMobileDevices, 
            @Qualifier("availableTelevisions")ToolCallback availableTelevisions, 
            @Qualifier("availableAccesories")ToolCallback availableAccesories) {
        this.chatClient = chatClientBuilder.defaultSystem(
                "Youre a helpful AI assitant in an online electronic store " + 
                        "helping customers with their inquiries and providing " + 
                        "advice on buying electronic devices."
        ).defaultTools(availableLaptops, availableMobileDevices, 
                availableTelevisions, availableAccesories)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    @Override
    public String processMessage(String message, String sessionId) {
        return chatClient.prompt(message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, sessionId))
                .call().content();
    }
    
}
