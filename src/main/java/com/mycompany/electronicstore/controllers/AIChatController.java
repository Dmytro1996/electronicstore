/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.service.AiChatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/aichat")
public class AIChatController {

    private final AiChatService aiChatService;
    
    public AIChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }
    
    @PostMapping("/send-message")
    public String receiveMessage(@RequestParam("message")String message) {
        return aiChatService.processMessage(message);
    }
}
