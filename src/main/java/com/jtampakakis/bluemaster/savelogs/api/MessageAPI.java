package com.jtampakakis.bluemaster.savelogs.api;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jtampakakis.bluemaster.savelogs.Startup;
import com.jtampakakis.bluemaster.savelogs.entities.MessageEntity;
import com.jtampakakis.bluemaster.savelogs.services.MessageService;

@RestController
@RequestMapping(path = "/messages")
public class MessageAPI {

    @Autowired
    private MessageService messageService;

    private final static Logger LOGGER = Startup.LOGGER;

    @GetMapping(path = "", produces = { "application/json" })
    public @ResponseBody List<MessageEntity> getAllMessages() {
        LOGGER.info("GET /messages: Requested all messages");
        return messageService.getAllMessages();
    }

    @GetMapping(path = "/{id}", produces = { "application/json" })
    public @ResponseBody MessageEntity getMessageById(@PathVariable Long id) {
        LOGGER.info("GET /messages/{id}: Requested message with ID " + id);
        return messageService.getMessageById(id);
    }

}
