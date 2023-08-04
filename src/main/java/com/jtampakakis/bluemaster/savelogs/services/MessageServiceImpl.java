// MessageServiceImpl class

package com.jtampakakis.bluemaster.savelogs.services;

import com.jtampakakis.bluemaster.savelogs.entities.MessageEntity;
import com.jtampakakis.bluemaster.savelogs.rabbitmq.StandardMessage;
import com.jtampakakis.bluemaster.savelogs.repos.MessageEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageEntityRepo messageEntityRepo;

    @Autowired
    public MessageServiceImpl(MessageEntityRepo messageEntityRepo) {
        this.messageEntityRepo = messageEntityRepo;
    }

    @Override
    public void convertAndSave(StandardMessage msg) throws RuntimeException {
        try {
            MessageEntity messageEntity = convertStandardMessageToMessageEntity(msg);
            messageEntityRepo.save(messageEntity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save message to MySQL database", e);
        }
    }
    @Override
    public List<MessageEntity> getAllMessages() {
        return messageEntityRepo.findAll();
    }

    @Override
    public MessageEntity getMessageById(Long id) {
        return messageEntityRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Message with id " + id + " not found."));
    }
    private MessageEntity convertStandardMessageToMessageEntity(StandardMessage msg) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setUuid(msg.getUuid());
        messageEntity.setReplyToUuid(msg.getReplyToUuid());
        messageEntity.setDateSent(msg.getDateSent());
        messageEntity.setSender(msg.getSender());
        messageEntity.setTopic(msg.getTopic());
        messageEntity.setExchange(msg.getExchange());
        messageEntity.setMsgContent(msg.getMsgContent());
        messageEntity.setTargetObject(msg.getTargetObject());
        return messageEntity;
    }
}
