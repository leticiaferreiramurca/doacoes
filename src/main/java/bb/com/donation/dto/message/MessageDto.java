package bb.com.donation.dto.message;

import bb.com.donation.dto.person.PersonDto;
import bb.com.donation.model.Message;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
public class MessageDto implements Serializable {
    private final String subject;
    private final String bodyMessage;
    private final PersonDto person_to;
    private final PersonDto person_by;
    private final Long lastMessage;


    public MessageDto(String subject, String bodyMessage, PersonDto person_by, PersonDto person_to, Long lastMessage){
        this.subject = subject;
        this.bodyMessage = bodyMessage;
        this.person_by = person_by;
        this.person_to = person_to;
        this.lastMessage = lastMessage;
    }
    public Message toMessage() {
        return Message.builder()
                .subject(subject)
                .bodyMessage(bodyMessage)
                .person_to(person_to.toPerson())
                .person_by(person_by.toPerson())
//                .lastMessage(Message.builder ().id (lastMessage).build ())
                .build();
    }
}
