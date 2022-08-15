package bb.com.donation.service.impl;

import bb.com.donation.dto.message.MessageDto;
import bb.com.donation.model.Message;
import bb.com.donation.repository.MessageRepository;
import bb.com.donation.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;


    public MessageServiceImp(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(MessageDto t) {
//        TODO: verificar se o id da lastMessage existe no banco de dados, caso exista anexar na message.
        Message message = t.toMessage ();
        return messageRepository.save(message);
    }

    @Override
    public Message getById(Long aLong) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll ();
    }

    @Override
    public void delete(Long aLong) {

    }
}
