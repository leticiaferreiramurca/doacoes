package bb.com.donation.dto.message;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class MessageSaveDTO  {
    private String subject;
    private String bodyMessage;
    private Long personTo;
    private Long personBy;
    private Long lastMessage;

    private Long donationId;
    public MessageSaveDTO(String subject, String bodyMessage, Long personBy, Long personTo, Long lastMessage, Long donationId) {
        this.subject = subject;
        this.bodyMessage = bodyMessage;
        this.personBy = personBy;
        this.personTo = personTo;
        this.lastMessage = lastMessage;
        this.donationId = donationId;
    }
}
