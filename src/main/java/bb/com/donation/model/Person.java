package bb.com.donation.model;

import lombok.*;
import org.hibernate.Hibernate;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Include
    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "person", orphanRemoval = true)
    private Set<Product> products = new LinkedHashSet<> ();

    @OneToMany(mappedBy = "personOwner", orphanRemoval = true)
    private Set<Donation> donations = new LinkedHashSet<> ();

    @ManyToOne
    @JoinColumn(name = "donation_requests_id")
    private Donation donation_Requests;

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
