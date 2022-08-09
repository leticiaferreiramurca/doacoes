package bb.com.donation.model;

import bb.com.donation.enums.ConditionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private java.lang.Long id;

    @Column(name = "name", nullable = true, length = 100)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personId")
    private Person person;

    @Column(name = "condition_type", nullable = false)
    private ConditionType conditionType;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Column(name = "is_valid")
    private Boolean isValid;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private Donation donation;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}