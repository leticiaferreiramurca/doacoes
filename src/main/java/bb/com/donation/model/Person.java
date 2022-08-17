package bb.com.donation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Builder
public class Person {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Include
    @Column(name = "name", length = 100)
    private String name;



    @JsonManagedReference(value = "person_donation")
    @OneToMany(mappedBy = "personOwner", orphanRemoval = true)
    private Set<Donation> donations = new LinkedHashSet<> ();

//    @JsonManagedReference(value = "person_product")
    @OneToMany(mappedBy = "person", cascade = CascadeType.MERGE, orphanRemoval = true)
    @Fetch (FetchMode.SUBSELECT)
    private Set<Product> products = new LinkedHashSet<> ();


//    @JsonManagedReference(value = "person_message")
    @ManyToOne
    @JsonBackReference(value = "person_message")
    @JoinColumn(name = "donation_requests_id")
    private Donation donation_Requests;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "donation_id")
    private Donation donation;

    @ManyToOne
    @JoinColumn(name = "donation_Interessed_id")
    private Donation donation_Interessed;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass (this) != Hibernate.getClass (o)) return false;
        Person person = (Person) o;
        return id != null && Objects.equals (id, person.id);
    }

    @Override
    public int hashCode() {
        return getClass ().hashCode ();
    }
}
