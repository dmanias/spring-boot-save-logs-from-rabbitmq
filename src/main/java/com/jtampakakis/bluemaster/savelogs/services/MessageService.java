package com.jtampakakis.bluemaster.savelogs.services;

import com.jtampakakis.bluemaster.savelogs.entities.MessageEntity;
import com.jtampakakis.bluemaster.savelogs.rabbitmq.StandardMessage;

import java.util.List;

public interface MessageService {
    void convertAndSave(StandardMessage msg);

    List<MessageEntity> getAllMessages();
    MessageEntity getMessageById(Long id);

}
