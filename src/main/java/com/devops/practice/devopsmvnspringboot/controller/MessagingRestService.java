package com.devops.practice.devopsmvnspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devops.practice.devopsmvnspringboot.entity.Message;

@RestController	
@RequestMapping(path = "/messaging")
public class MessagingRestService {
//http://localhost:9001/messaging/message?name='Name'
@GetMapping(
    path = "/message",
    params = {"name"})
public Message getMessage(
        @RequestParam final String name) {
      
  final Message message = new Message();
  message.setText("Hello " + name +", "+"How are you!");
  return message;
    
}
}

