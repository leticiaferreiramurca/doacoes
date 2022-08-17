package bb.com.donation.service.impl;

import bb.com.donation.dto.message.MessageDto;
import bb.com.donation.exceptions.ErrorMessages;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Donation;
import bb.com.donation.model.Message;
import bb.com.donation.model.Person;
import bb.com.donation.repository.MessageRepository;
import bb.com.donation.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    private final PersonServiceImp personService;
    private final DonationServiceImp donationService;


    public MessageServiceImp(MessageRepository messageRepository, ModelMapper modelMapper, PersonServiceImp personService, DonationServiceImp donationService) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.personService = personService;
        this.donationService = donationService;
    }

    @Override
    @Transactional
    public Message save(MessageDto messageDto) {
        Message message = convertToEntity (messageDto);
        message.setPersonBy (personService.getById (messageDto.getPersonBy ()));
        message.setPersonTo (personService.getById (messageDto.getPersonTo ()));
        Donation donation = donationService.getById (messageDto.getDonationId ());
        if (Objects.isNull (donation)) {
            throw new ValidacaoException (ErrorMessages.DONATION_NOT_FOUND);
        }
        final Set<Person> persons = donation.getPersonInterested ();
        if(Objects.isNull (message.getPersonBy ()) || Objects.isNull (message.getPersonTo ())) {
            throw new ValidacaoException (ErrorMessages.PERSON_NOT_FOUND);
        }
//       Check if the personBy is the donation's owner or if it is between Persons interested in the donation
        if(!message.getPersonBy ().getId ().equals (donation.getPersonOwner ().getId ()) &&
            !persons.contains (message.getPersonBy ())
        ){
            throw new ValidacaoException (ErrorMessages.PERSON_NOT_ALLOWED);
        }

//       Check if the personTo is the donation's owner or if it is between Persons interested in the donation
        if(!message.getPersonTo ().getId ().equals (donation.getPersonOwner ().getId ()) &&
            !persons.contains (message.getPersonTo ())
        ){
            throw new ValidacaoException (ErrorMessages.PERSON_NOT_ALLOWED);
        }


        if(message.getLastMessage ().equals (message.getId ())) {
            throw new ValidacaoException (ErrorMessages.MESSAGE_NOT_ALLOWED_SELF_REFERENCE);
        }
        if(message.getPersonBy ().equals (message.getPersonTo ())) {
            throw new ValidacaoException (ErrorMessages.MESSAGE_NOT_ALLOWED_PERSONTO_SELF_REFERENCE);
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
    @Transactional(readOnly = true)
    public Message getById(Long aLong) {
        return messageRepository.findById(aLong).orElseThrow(() -> new ValidacaoException(ErrorMessages.MESSAGE_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAll() {
        return messageRepository.findAll ();
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        Message message = messageRepository.findById (aLong).orElseThrow (
                () -> new ValidacaoException ("Message not found")
        );
        messageRepository.delete (message);
    }

    private Message convertToEntity(MessageDto messageDto) {

        return modelMapper.map(messageDto, Message.class);
    }
}
