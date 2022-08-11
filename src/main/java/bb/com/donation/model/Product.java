package bb.com.donation.model;

import bb.com.donation.enums.ConditionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private java.lang.Long id;

    @Column(name = "name", nullable = true, length = 100)
    private String name;


    @Column(name = "condition_type", nullable = false)
    private ConditionType conditionType;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Column(name = "is_valid")
    private Boolean isValid;

    @JsonBackReference(value = "product_person")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    private Person person;


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private Post post;



    @JsonBackReference(value = "product_donation")
    @OneToOne(mappedBy = "product", orphanRemoval = true)
    private Donation donation;

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