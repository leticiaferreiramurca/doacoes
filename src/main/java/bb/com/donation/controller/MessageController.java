package bb.com.donation.controller;

import bb.com.donation.MessageDto;
import bb.com.donation.model.Message;
import bb.com.donation.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @PostMapping()
    public Message save(@RequestBody MessageDto message) {

        return messageService.save(message);
    }


}
