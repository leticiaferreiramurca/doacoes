package bb.com.donation.dto.message;

import bb.com.donation.model.Message;
import bb.com.donation.model.Person;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
public class MessageDto implements Serializable {
    private final Long id;
    private final String subject;
    private final String bodyMessage;
    private final Long personTo;
    private final Long personBy;
    private final Long lastMessage;

    private final Long donationId;


    public MessageDto(Long id, String subject, String bodyMessage, Long personBy, Long personTo, Long lastMessage, Long donationId){
        this.id = id;
        this.subject = subject;
        this.bodyMessage = bodyMessage;
        this.personBy = personBy;
        this.personTo = personTo;
        this.lastMessage = lastMessage;
        this.donationId = donationId;
    }
    public Message toMessage() {
        return Message.builder()
                .subject(subject)
                .bodyMessage(bodyMessage)
                .personTo (Person.builder().id (personTo).build())
                .personBy (Person.builder().id (personBy).build())
                .lastMessage(lastMessage)
                .build();
    }
}
