package com.ProgrammationWeb.Blog.Tchat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ProgrammationWeb.Blog.RestAPI.ChatMessage;

@Controller
public class GreetingController {


    @MessageMapping("/greeting")
    @SendTo("/topic/greetings")
    public ChatMessage greeting(String message) throws Exception {
        //Thread.sleep(3000); // simulated delay
        return new ChatMessage("Hello, " + message + "!");
    }
}