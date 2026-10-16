package com.mycompany.electronicstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.electronicstore.service.AiChatService;
import java.util.List;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.ai.tool.ToolCallback;
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
    
    private final GoogleGenAiChatModel chatModel;
    
    private final GoogleGenAiChatOptions googleGenAiChatOptions;
    
    private final Message systemMessage;
    
    @Autowired
    public AiChatServiceImpl(GoogleGenAiChatModel chatModel, 
            ToolCallback availableLaptops) {
        this.chatModel = chatModel;
        this.googleGenAiChatOptions = GoogleGenAiChatOptions.builder()
                .toolCallbacks(availableLaptops).build();
        this.systemMessage = new SystemPromptTemplate(
                "Youre a helpful AI assitant in an online electronic store " + 
                        "helping customers with their inquiries and providing " + 
                        "advice on buying electronic devices.").createMessage();
    }

    @Override
    public String processMessage(String message) {
        return chatModel.call(
                new Prompt(
                        List.of(new UserMessage(message), systemMessage),
                        googleGenAiChatOptions)
        ).getResult().getOutput().getText();
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
