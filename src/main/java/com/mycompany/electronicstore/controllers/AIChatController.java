/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.model.AIChatMessage;
import com.mycompany.electronicstore.service.AiChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/aichat")
public class AIChatController {

    private final AiChatService aiChatService;
    
    public AIChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }
    
    @PostMapping("/send-message")
    public AIChatMessage receiveMessage(@RequestParam("message")String message) {
        return new AIChatMessage(aiChatService.processMessage(message));
    }
}
