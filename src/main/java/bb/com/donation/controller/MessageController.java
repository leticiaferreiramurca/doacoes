package bb.com.donation.controller;

import bb.com.donation.dto.message.MessageDto;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Message;
import bb.com.donation.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    private final ModelMapper modelMapper;


    public MessageController(MessageService messageService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    @Operation(summary = "List All Messages", tags = {"Messages"})
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @PostMapping()
    @Operation(summary = "Save Message", tags = {"Messages"})
    public ResponseEntity<MessageDto> save(@RequestBody @Valid MessageDto message) {

        try {
            Message messageSaved = messageService.save (message);
            MessageDto messageDto = MessageDto.builder()
                    .id (messageSaved.getId ())
                    .subject (messageSaved.getSubject ())
                    .bodyMessage (messageSaved.getBodyMessage ())
                    .personTo (messageSaved.getPersonTo ().getId ())
                    .personBy (messageSaved.getPersonBy ().getId ())
                    .lastMessage (messageSaved.getLastMessage ())
                    .build ();
            return ResponseEntity.ok (messageDto);
        } catch (ValidacaoException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Message by ID")
    public ResponseEntity<MessageDto> getById(@PathVariable @Valid Long id) {
        MessageDto messageDto = convertToDto (messageService.getById (id));
        return ResponseEntity.ok (messageDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Message", tags = {"Messages"})
    public ResponseEntity<String> delete(@PathVariable @Valid Long id) {
        messageService.delete(id);
        return ResponseEntity.ok("Message deleted");
    }

    private MessageDto convertToDto(Message message) {
        MessageDto messageDto = modelMapper.map (message, MessageDto.class);
        return messageDto;
    }





}
