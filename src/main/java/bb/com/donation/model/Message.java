package bb.com.donation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message", nullable = false)
    private String bodyMessage;

    @ManyToOne
    @JoinColumn(name = "person_to_id")
    @JsonManagedReference(value = "message_person")
    private Person person_to;

    @ManyToOne
    @JsonManagedReference(value = "message_person")
    @JoinColumn(name = "person_by_id")
    private Person person_by;

    @JsonManagedReference(value = "message_message")
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "last_message_id")
    private Message lastMessage;

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Person getPerson_by() {
        return person_by;
    }

    public void setPerson_by(Person person_by) {
        this.person_by = person_by;
    }

    public Person getPerson_to() {
        return person_to;
    }

    public void setPerson_to(Person person_to) {
        this.person_to = person_to;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String message) {
        this.bodyMessage = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}