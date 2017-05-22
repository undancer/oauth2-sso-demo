package com.example.web;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by undancer on 2017/5/23.
 */
@RestController
@RequestMapping(path = "/api/messages")
public class MessageController {

    final List<Message> messages = Collections.synchronizedList(new LinkedList<>());

    @GetMapping
    List<Message> getMessages(Principal principal) {
        return messages;
    }

    @PostMapping
    Message postMessage(Principal principal, @RequestBody Message message) {
        message.username = principal.getName();
        message.createdAt = LocalDateTime.now();
        messages.add(0, message);
        return message;
    }
}
