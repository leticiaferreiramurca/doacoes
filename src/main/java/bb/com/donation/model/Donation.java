package bb.com.donation.model;

import bb.com.donation.enums.ConditionType;
import bb.com.donation.enums.DonationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "donation")
public class Donation {

//    TODO: filtro por comunidade e inserir o campo comunidade_id na tabela donation
//    TODO: filtro por pessoasInteressadas e por minhas doacoes e inserir o campo person_id na tabela donation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at")
    @JsonIgnore
    private LocalDate createdAt;


    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "donation_status", nullable = false)
    private DonationStatus donationStatus;

    @JsonBackReference(value = "donation_person")
    @ManyToOne(cascade = CascadeType.ALL, optional = false,fetch = FetchType.EAGER, targetEntity = Person.class)
    @JoinColumn(name = "person_owner_id", nullable = false)
    private Person personOwner;





    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "last_message_id")
    @JsonBackReference(value = "donation_message")
    private Message lastMessage;

    @JsonManagedReference(value = "donation_product")
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "donation_person_interested",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "person_interested_id"))
    private Set<Person> personInterested = new LinkedHashSet<> ();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Donation donation = (Donation) o;
        return id != null && Objects.equals(id, donation.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}