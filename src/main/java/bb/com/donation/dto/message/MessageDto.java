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
    private Long id;
    private String subject;
    private String bodyMessage;
    private Long personTo;
    private Long personBy;
    private Long lastMessage;

    private Long donationId;


    public MessageDto(){}
    public MessageDto(Long id, String subject, String bodyMessage, Long personBy, Long personTo, Long lastMessage, Long donationId){
        this.id = id;
        this.subject = subject;
        this.bodyMessage = bodyMessage;
        this.personBy = personBy;
        this.personTo = personTo;
        this.lastMessage = lastMessage;
        this.donationId = donationId;
    }
}
