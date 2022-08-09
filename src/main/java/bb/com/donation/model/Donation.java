package bb.com.donation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at")
    @JsonIgnore
    private LocalDate createdAt;


    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "person_owner_id", nullable = false)
    private Person personOwner;

    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new LinkedHashSet<> ();

    @OneToMany(mappedBy = "donation_Requests", orphanRemoval = true)
    private Set<Person> personsInterested = new LinkedHashSet<> ();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "last_message_id")
    private Message lastMessage;

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Set<Person> getPersonsInterested() {
        return personsInterested;
    }

    public void setPersonsInterested(Set<Person> persons) {
        this.personsInterested = persons;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Person getPersonOwner() {
        return personOwner;
    }

    public void setPersonOwner(Person personOwner) {
        this.personOwner = personOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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