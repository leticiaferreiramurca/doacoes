package bb.com.donation.service.impl;

import bb.com.donation.dto.message.MessageDto;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Message;
import bb.com.donation.repository.MessageRepository;
import bb.com.donation.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final PersonServiceImp personService;


    public MessageServiceImp(MessageRepository messageRepository, ModelMapper modelMapper, PersonServiceImp personService) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.personService = personService;
    }

    @Override
    public Message save(MessageDto messageDto) {
        Message message = convertToEntity (messageDto);
        message.setPersonBy (personService.getById (messageDto.getPersonBy ()));
        message.setPersonTo (personService.getById (messageDto.getPersonTo ()));


        if(message.getLastMessage ().equals (message.getId ())) {
            throw new ValidacaoException ("Last message can't be the same as message id");
        }
        if(message.getPersonBy ().equals (message.getPersonTo ())) {
            throw new ValidacaoException ("Person to can't be the same as person by");
        }
        if(message.getBodyMessage ().isEmpty ()) {
            throw new ValidacaoException ("Body message can't be empty");
        }
        if(message.getSubject ().isEmpty ()) {
            throw new ValidacaoException ("Subject can't be empty");
        }
        if(message.getPersonTo () == null) {
            throw new ValidacaoException ("Person to can't be null");
        }
        if(!Objects.equals (message.getLastMessage (), 0L)) {
            messageRepository.findById (messageDto.getLastMessage ()).orElseThrow (
                    () -> new ValidacaoException ("Last message not found")
            );
        }
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
        Message message = messageRepository.findById (aLong).orElseThrow (
                () -> new ValidacaoException ("Message not found")
        );
        messageRepository.delete (message);
    }

    private MessageDto convertToDto(Message message) {
        return modelMapper.map(message, MessageDto.class);
    }

    private Message convertToEntity(MessageDto messageDto) {

        return modelMapper.map(messageDto, Message.class);
    }
}
