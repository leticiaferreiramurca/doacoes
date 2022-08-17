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
    private Person personTo;

    @ManyToOne
    @JsonManagedReference(value = "message_person")
    @JoinColumn(name = "person_by_id")
    private Person personBy;

//    @JsonManagedReference(value = "message_message")
//    @OneToOne(orphanRemoval = true)
//    @JoinColumn(name = "last_message_id", nullable = true)
//    @Fetch (FetchMode.JOIN)
    private Long lastMessage;


    public Person getPersonBy() {
        return personBy;
    }

    public void setPersonBy(Person person_by) {
        this.personBy = person_by;
    }

    public Person getPersonTo() {
        return personTo;
    }

    public void setPersonTo(Person person_to) {
        this.personTo = person_to;
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