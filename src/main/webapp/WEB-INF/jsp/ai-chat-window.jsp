<%-- 
    Document   : ai-chat-window
    Created on : Oct 28, 2026, 3:52:40 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<button class="ai-chat-open-button" onclick="openForm()">Chat</button>

<div class="ai-chat-popup" id="myForm">
  <div class="ai-chat-container">
    <h1>Chat</h1>

    <textarea placeholder="Hi! I'm your AI Assistant. How can I help you?" name="chat-history" id = 'chat-history' required></textarea>
    <input placeholder="Type message.." name="ai-message-input" id="ai-message-input" required></input>
    <button type="submit" class="send-to-ai-btn" id = 'send-to-ai-btn' onclick="send_to_ai_chat()">Send</button>
    <button type="button" class="ai-chat-close-btn" onclick="closeForm()">Close</button>
  </div>
</div>
